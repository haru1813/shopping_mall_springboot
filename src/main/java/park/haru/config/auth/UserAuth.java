package park.haru.config.auth;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserAuth implements UserDetails {

    private User user;

    public UserAuth(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(()->{
            return user.getHaruMarket_user_role();
        });
        return authorities;
    }

    public int getIndex(){return user.getHaruMarket_user_index();}

    @Override
    public String getPassword() {
        return user.getHaruMarket_user_pw();
    }

    @Override
    public String getUsername() {
        return user.getHaruMarket_user_id();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
