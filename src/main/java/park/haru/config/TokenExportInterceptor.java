package park.haru.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import park.haru.config.jwt.JwtProperties;
import park.haru.config.jwt.TokenProvider;

@RequiredArgsConstructor
public class TokenExportInterceptor implements HandlerInterceptor {

    private final TokenProvider tokenProvider;

    public static int haruMarket_user_index = 0;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String TOKEN_PREFIX = "Bearer ";
        String HEADER_STRING = "Authorization";
        String header = request.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)){
            return true;
        }

        String token = request.getHeader(HEADER_STRING)
                .replace(TOKEN_PREFIX, "");

        if(tokenProvider.validToken(token)){
            TokenExportInterceptor.haruMarket_user_index = Integer.parseInt(tokenProvider.getHaruMarket_user_index(token));
        }

        return true;
    }
}
