package park.haru.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import park.haru.common.service.LoginService;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAuthService implements UserDetailsService {

    @Autowired
    private LoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("UserAuthService : 진입");
        List<HashMap<String, Object>> list = loginService.Authentication(username);

        User user = new User();
        user.setHaruMarket_user_index((Integer) list.get(0).get("haruMarket_user_index"));
        user.setHaruMarket_user_id((String) list.get(0).get("haruMarket_user_id"));
        user.setHaruMarket_user_pw((String) list.get(0).get("haruMarket_user_pw"));

        return new UserAuth(user);
    }
}
