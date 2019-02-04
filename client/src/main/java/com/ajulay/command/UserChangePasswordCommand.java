package com.ajulay.command;

import com.ajulay.api.IController;
import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.Success;
import com.ajulay.endpoint.User;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.event.Observes;

public class UserChangePasswordCommand extends AbstractCommand {
    @Override
    public String getCommandKeyWord() {
        return "/chpwd";
    }

    @Override
    public String getDescription() {
        return "change password";
    }

    public void execute(@Observes @Nullable UserChangePasswordCommand userChangePasswordCommand) {
        @Nullable final IController controller = getController();
        if (controller == null) {
            System.out.println("Something wrong... Try again.");
            toBegin();
            return;
        }
        @Nullable final Session session = controller.getCurrentSession();
        if (session == null) {
            System.out.println("You are log out.");
            toBegin();
            return;
        }
        @Nullable final String newPassword = controller.nextLine();
        if (newPassword == null || newPassword.isEmpty()) {
            System.out.println("Incorrect data...");
            toBegin();
            return;
        }
        @Nullable final User user = controller.getUserService().getUserSoapEndPointPort().findById(session, session.getUserId());
        if (user == null) {
            System.out.println("Something wrong...");
            toBegin();
            return;
        }
        @Nullable final Success success = controller.getUserService().getUserSoapEndPointPort().changePassword(session, user, newPassword);
        if (success == null) {
            System.out.println("Check connect.");
            toBegin();
            return;
        }
        System.out.println("[Ok]");
        toBegin();
    }

}
