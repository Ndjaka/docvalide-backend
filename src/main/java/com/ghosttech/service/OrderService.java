package com.ghosttech.service;

import com.ghosttech.constants.DocValidConstant;
import com.ghosttech.constants.MessageConstant;
import com.ghosttech.dao.OrdersDao;
import com.ghosttech.dao.UserDao;
import com.ghosttech.dto.OrderRequest;
import com.ghosttech.exception.NotFoundException;
import com.ghosttech.model.MessageRequest;
import com.ghosttech.model.MessageResponse;
import com.ghosttech.model.Orders;
import com.ghosttech.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.UUID;


@Service
@AllArgsConstructor
@Slf4j
public class OrderService  {
    private final OrdersDao ordersDao;
    private final UserDao userDao;
    private final MessageService messageService;
    private final EmailService emailService;
    private final EnvironmentService environmentService;


    /**
     * Add order.
     * @param orderRequest the order request
     * @param userId the user id
     *
     */
    public void addOrder(OrderRequest orderRequest, UUID userId) {
        log.info("addOrder : {}", orderRequest);

        User user = userDao.getUserById(userId)
                .orElseThrow(() -> new NotFoundException("user not found", "USER_NOT_FOUND"));

        Orders order = Orders.builder()
                .id(UUID.randomUUID())
                .orderType(orderRequest.getOrderType())
                .orderAmount(orderRequest.getOrderAmount())
                .orderStatus(DocValidConstant.PENDING)
                .orderNumber(orderRequest.getOrderNumber())
                .orderDate(Instant.now())
                .userId(userId)
                .build();

        int insertOrderId = ordersDao.insertOrder(order);

        if (insertOrderId == 1) {
            String orderTypeMessage = (orderRequest.getOrderType().equals(DocValidConstant.CRIMINAL_RECORD))
                    ? "vient de faire une demande de casier judiciaire"
                    : "vient de faire une demande de légalisation de document";

            String message = user.getFirstname() + " " + user.getLastname() + " " + orderTypeMessage;

            sendAdminNotification(message);
            sendUserNotification(user);

        } else {
            throw new IllegalStateException("Oops, something went wrong");
        }
    }
    private void sendUserNotification(User user) {
        String confirmationMessage = "Votre demande a été bien reçue. Vous serez contacté dans les plus brefs délais.";

        String emailBody = "Bonjour " + user.getFirstname() + " " + user.getLastname() + ",\n\n" +
                           confirmationMessage + "\n\n" +
                           "Cordialement,\n" +
                           "L'équipe DocValide";

        if (!environmentService.getActiveEnvironment().equals("local")) {
            MessageRequest messageRequestToUser = MessageRequest.builder()
                    .user_id(MessageConstant.USER_ID)
                    .password(MessageConstant.PASSWORD_ADMIN)
                    .phone_str(user.getPhoneNumber())
                    .sender_name(MessageConstant.SENDER_NAME)
                    .message(confirmationMessage)
                    .build();

            MessageResponse messageResponseToUser = messageService.sendMessage(messageRequestToUser);
            String errorMessage = "L'utilisateur " + user.getFirstname() + " " + user.getLastname() + " n'a pas reçu le message de confirmation. Veuillez le/la contacter au numéro suivant : " +
                                  user.getPhoneNumber() + ".\n\n" +
                                  "Nous souhaitons également vous informer que votre crédit SMS actuel s'élève à " + messageResponseToUser.getData().getCredit_sms() + ".\n\n" +
                                  "Cordialement,\n";

            if (messageResponseToUser.isSuccess()) {
                log.info("Message sent successfully to user");
            } else {
                emailService.sendMail(null, MessageConstant.EMAIL_ADMIN, MessageConstant.cc, "Demande Non Reçue par l'utilisateur", errorMessage);
                log.error("Message not sent to user");
            }
        }
        emailService.sendMail(null,user.getEmail(),null, "Demande reçue", emailBody);

    }

    private void sendAdminNotification(String message) {
        MessageRequest messageRequestToAdmin = MessageRequest.builder()
                .user_id(MessageConstant.USER_ID)
                .password(MessageConstant.PASSWORD_ADMIN)
                .phone_str(MessageConstant.PHONE_STR_ADMIN)
                .sender_name(MessageConstant.SENDER_NAME)
                .message(message)
                .build();

        if (!environmentService.getActiveEnvironment().equals("local")) {
            MessageResponse messageResponseToAdmin = messageService.sendMessage(messageRequestToAdmin);

            String adminErrorMessage = "L'administrateur n'a pas reçu le message de confirmation. Veuillez le/la contacter au numéro suivant : " +
                                       MessageConstant.PHONE_STR_ADMIN + ".\n\n" +
                                       "Nous souhaitons également vous informer que votre crédit SMS actuel s'élève à " + messageResponseToAdmin.getData().getCredit_sms() + ".\n\n" +
                                       "Cordialement,\n";

            if (messageResponseToAdmin.isSuccess()) {
                log.info("Message sent successfully to admin");
            } else {
                emailService.sendMail(null, MessageConstant.EMAIL_ADMIN, MessageConstant.cc, "Demande Non Reçue par l'administrateur", adminErrorMessage);
                log.error("Message not sent to admin");
            }
        }

        emailService.sendMail(null, MessageConstant.EMAIL_ADMIN, MessageConstant.cc, "Nouvelle demande", message);
    }


}
