package com.outrider.web;

import static com.outrider.cons.OutriderConstants.*;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by jonatan on 09/12/2016.
 */
@SpringUI(path = "/login")
@Title("LoginPage")
@Theme("reindeer")
public class LoginUi extends UI {

    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;
    private TextField user;
    private PasswordField password;
    private Button loginButton;
    private Notification notificationMessage;
    private Label developedByLabel;


    @Override
    protected void init(VaadinRequest request) {

        setSizeFull();

        notificationMessage = new Notification(LoginConstants.LOGIN_FAIL_MESSAGE_TITLE, LoginConstants.LOGIN_FAIL_MESSAGE, Notification.TYPE_WARNING_MESSAGE);
        notificationMessage.setDelayMsec(20000);
        notificationMessage.setPosition(Position.TOP_CENTER);

        loginButton = new Button(LoginConstants.LOGIN, this::loginButtonClick);

        user = new TextField(LoginConstants.USER_LABEL);
        user.setWidth(300, Unit.PIXELS);
        user.setRequired(true);
        user.setInputPrompt(LoginConstants.USER_INPUT);

        password = new PasswordField(LoginConstants.PASSWORD_LABEL);
        password.setWidth(300, Unit.PIXELS);
        password.setRequired(true);
        password.setValue(EMPTY_VALUE);
        password.setNullRepresentation(EMPTY_VALUE);

        developedByLabel = new Label(LoginConstants.DEVELOPED_BY_LABEL, ContentMode.HTML);

        VerticalLayout fields = new VerticalLayout(user, password, loginButton, developedByLabel);
        fields.setCaption(LoginConstants.LOGIN_MAIN_CAPTION);
        fields.setSpacing(true);
        fields.setMargin(new MarginInfo(true, true, true, false));
        fields.setSizeUndefined();
        fields.setCaptionAsHtml(true);

        VerticalLayout uiLayout = new VerticalLayout(fields);
        uiLayout.setSizeFull();
        uiLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
        setStyleName(Reindeer.LAYOUT_BLUE);
        setFocusedComponent(user);

        setContent(uiLayout);
    }

    public void loginButtonClick(Button.ClickEvent e) {
        try {

            Authentication auth = new UsernamePasswordAuthenticationToken(user.getValue(),password.getValue());
            Authentication authenticated = daoAuthenticationProvider.authenticate(auth);
            SecurityContextHolder.getContext().setAuthentication(authenticated);

            getPage().setLocation(PageView.MAIN_PAGE);

        } catch (AuthenticationException ex) {
            notificationMessage.show(Page.getCurrent());
        }
    }
}