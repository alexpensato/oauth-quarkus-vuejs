package io.betweendata;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.security.identity.SecurityIdentity;
import org.jboss.resteasy.annotations.cache.NoCache;

/**
 * See:
 *    https://www.keycloak.org/docs/4.8/authorization_services/
 *    https://kzwiev.info/u/z8KzqqFuep9mYKc/quarkus-6-seguran-a-com-keycloak
 *    http://www.quabr.com/69123537/why-is-quarkus-oidc-credentials-secret-being-ignored
 *    https://github.com/quarkusio/quarkus-quickstarts/blob/main/security-openid-connect-quickstart/config/quarkus-realm.json#L395
 */
@Path("/")
@RolesAllowed("user")
@RequestScoped
public class ProtectedResource {
  @Inject
  SecurityIdentity securityContext;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/secretMessage")
  @NoCache
  public SecretMessage secretMessage() {
    return new SecretMessage("This is a secret message available only to authenticated users.");
  }

  public class SecretMessage {
    private String message;

    public SecretMessage(String message) {
      this.message = message;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }
  }
}
