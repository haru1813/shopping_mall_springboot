package park.haru.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import park.haru.common.service.LoginService;
import park.haru.config.auth.User;
import park.haru.config.auth.UserAuth;
import park.haru.config.jwt.TokenProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

// 인가
public class AuthorizationFilter extends BasicAuthenticationFilter {

    private LoginService loginService;
    private TokenProvider tokenProvider;

    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";

    public AuthorizationFilter(AuthenticationManager authenticationManager, LoginService loginService,TokenProvider tokenProvider) {
        super(authenticationManager);
        this.loginService = loginService;
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)){
            chain.doFilter(request, response);
            return;
        }

        String token = request.getHeader(HEADER_STRING)
                .replace(TOKEN_PREFIX, "");

        if(tokenProvider.validToken(token)){
            int haruMarket_user_index = Integer.parseInt(tokenProvider.getHaruMarket_user_index(token));
            List<HashMap<String,Object>> data = loginService.Authorization(haruMarket_user_index);

            User user = User.builder()
                    .haruMarket_user_index((Integer) data.get(0).get("haruMarket_user_index"))
                    .haruMarket_user_id((String) data.get(0).get("haruMarket_user_id"))
                    .haruMarket_user_pw((String) data.get(0).get("haruMarket_user_pw"))
                    .haruMarket_user_role((String) data.get(0).get("haruMarket_user_role"))
                    .build();

            UserAuth userAuth = new UserAuth(user);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userAuth,null,userAuth.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}
