package com.ajulay.command;

import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.Success;
import com.ajulay.endpoint.User;

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

    public void execute(@Observes UserChangePasswordCommand userChangePasswordCommand) {
        final Session session = getController().getCurrentSession();
        final String newPassword = getController().nextLine();
        final User user = getController().getUserService().getUserSoapEndPointPort().findById(session, session.getUserId());
        final Success success = getController().getUserService().getUserSoapEndPointPort().changePassword(session, user, newPassword);
        if (success == null) {
            System.out.println("Check connect.");
        }
    }

}
