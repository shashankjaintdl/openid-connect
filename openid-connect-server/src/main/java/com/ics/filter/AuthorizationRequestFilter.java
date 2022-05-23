package com.ics.filter;

import com.google.common.base.Strings;
import com.ics.oauth2.models.ClientDetailsEntity;
import com.ics.oauth2.service.ClientDetailEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.endpoint.RedirectResolver;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component("authRequestFilter")
@SuppressWarnings("deprecation")
public class AuthorizationRequestFilter extends GenericFilterBean {

    private static final String AUTHORIZATION_ENDPOINT = "/oauth2/authorize";

    private final OAuth2RequestFactory requestFactory;

    private final ClientDetailEntityService clientDetailEntityService;

    private final RedirectResolver redirectResolver;

    private RequestMatcher requestMatcher = new AntPathRequestMatcher(AUTHORIZATION_ENDPOINT);

    @Autowired
    public AuthorizationRequestFilter(OAuth2RequestFactory requestFactory,
                                      ClientDetailEntityService clientDetailEntityService,
                                      RedirectResolver redirectResolver) {
        this.requestFactory = requestFactory;
        this.clientDetailEntityService = clientDetailEntityService;
        this.redirectResolver = redirectResolver;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        if(!requestMatcher.matches(req)){
            chain.doFilter(req,res);
            return;
        }

        try {

            ClientDetailsEntity clientDetails = new ClientDetailsEntity();

            AuthorizationRequest authRequest = requestFactory.createAuthorizationRequest(extractAuthParameters(req.getParameterMap()));

            if(!Strings.isNullOrEmpty(authRequest.getClientId())){
                clientDetails = clientDetailEntityService.loadClientByClientId(authRequest.getClientId());
            }

            String uri = redirectResolver.resolveRedirect(authRequest.getRedirectUri(),clientDetails);
            UriBuilder uriBuilder = UriComponentsBuilder.fromUriString(uri);



            // TODO: Update it later to redirect
            res.sendRedirect(((UriComponentsBuilder) uriBuilder).toUriString());


        }
        catch (InvalidClientException e){
           chain.doFilter(req,res);
        }

    }


    private Map<String,String> extractAuthParameters(Map<String,String[]> requestParameter){
        Map<String, String> requestMap = new HashMap<>();
        requestParameter.keySet().forEach(x->{
                    requestMap.put(x,requestParameter.get(x)[0]);
                }
        );
        return requestMap;
    }

}
