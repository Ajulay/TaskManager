package com.ajulay.command;


import com.ajulay.api.IController;
import com.ajulay.constants.ServiceConstant;
import com.ajulay.endpoint.Session;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.event.Observes;
import java.util.List;

public class LoginCommand extends AbstractCommand {

    public final static String COMMAND = "/login";

    @Override
    public String getCommandKeyWord() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "login";
    }


    public void execute(@Observes LoginCommand loginCommand) throws Exception {
        @Nullable final IController controller = getController();
        if (controller == null) {
            System.out.println("Something wrong... Try again.");
            toBegin();
            return;
        }
        for (int i = 1; i <= ServiceConstant.MAX_ATTEMPT; i++) {
            System.out.println("Enter login:");
            @Nullable final String login = controller.nextLine();
            if (login == null || login.isEmpty()) {
                toBegin();
                return;
            }
            System.out.println("Enter password:");
            @NotNull final String passwordHash = controller.nextLine().hashCode() + "";
            @Nullable final Session session = controller.getSessionService().getSessionSoapEndPointPort().login(login, passwordHash);
            if (session == null) {
                System.out.println("Wrong login or password. Try again.");
                continue;
            }
            controller.setCurrentSession(session);
            break;
        }
        if (controller.getCurrentSession() == null) {
            System.out.println("You entered incorrect data more than 3 times.");
            toBegin();
            return;
        }
        @NotNull final List<Session> currentSessions = controller.getSessionService().getSessionSoapEndPointPort()
                .getSessionAll(controller.getCurrentSession());
        for (@NotNull final Session session : currentSessions) {
            System.out.printf("Session id: %s, signature: %s\n", session.getId(), session.getSignature());
        }
        controller.getCommands().clear();
        controller.registerCommandAll();
        System.out.println("[Ok]");
        toBegin();
    }

}
