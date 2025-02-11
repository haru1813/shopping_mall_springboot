package park.haru.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import park.haru.config.auth.User;
import park.haru.config.auth.UserAuth;
import park.haru.config.jwt.TokenProvider;

import java.io.IOException;
import java.time.Duration;

@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    public static final Duration ACCESS_TOKEN_DURATION = Duration.ofMinutes(30);
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";

    // login : post
    // 인증
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException{
        System.out.println("인증 시도");

        String haruMarket_user_id = request.getParameter("haruMarket_user_id");
        String haruMarket_user_pw = request.getParameter("haruMarket_user_pw");

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        haruMarket_user_id,
                        haruMarket_user_pw);

        Authentication authentication =
                authenticationManager.authenticate(authenticationToken);

        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException
    {
        UserAuth userAuth = (UserAuth) authResult.getPrincipal();
        User user = User.builder()
                .haruMarket_user_index(userAuth.getIndex())
                .build();

        response.addHeader(HEADER_STRING, TOKEN_PREFIX+tokenProvider.generateToken(user,ACCESS_TOKEN_DURATION));
    }
}
