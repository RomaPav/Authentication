package com.example.carhaulauthentication.service;

import com.example.carhaulauthentication.model.UserCarHaul;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class KeycloakUserService {
    private final Keycloak keycloak ;

    @Autowired
    public KeycloakUserService(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    public UserRepresentation createUser(UserCarHaul userCarHaul) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(userCarHaul.getLogin());
        user.setEmail(userCarHaul.getLogin());
        user.setFirstName(userCarHaul.getFullName());
//        user.setLastName(userCarHaul.getFullName().split(" ")[1]);
        user.setEmailVerified(false);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(userCarHaul.getPassword());
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

        List<CredentialRepresentation> list = new ArrayList<>();
        list.add(credentialRepresentation);
        user.setCredentials(list);

        UsersResource usersResource = getUsersResource();
        System.out.println(usersResource.toString());
        Response response = usersResource.create(user);
        System.out.println(response.getStatusInfo().toString());
        if (Objects.equals(201, response.getStatus())) {

            List<UserRepresentation> representationList = usersResource.searchByUsername(userCarHaul.getLogin(), true);
            if (!CollectionUtils.isEmpty(representationList)) {
                UserRepresentation userRepresentationFinal = representationList.stream()
                        .filter(userRepresentation -> Objects.equals(false, userRepresentation.isEmailVerified()))
                        .findFirst()
                        .orElse(null);
                assert userRepresentationFinal != null;
                log.info("Email was send to user id {}",userRepresentationFinal.getId());
                emailVerification(userRepresentationFinal.getId());
            }
            return user;
        }
        return user;
    }

    private UsersResource getUsersResource() {
        RealmResource realmResource = keycloak.realm("carhaul");
        return realmResource.users();
    }

    public UserRepresentation getUserById(String userId) {

        return getUsersResource().get(userId).toRepresentation();
    }

    public void deleteUserById(String userId) {
        getUsersResource().delete(userId);
    }

    public void emailVerification(String userId) {

        UsersResource usersResource = getUsersResource();
        usersResource.get(userId).sendVerifyEmail();
    }

}
