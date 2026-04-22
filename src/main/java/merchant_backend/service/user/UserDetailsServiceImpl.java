package merchant_backend.service.user;


import lombok.RequiredArgsConstructor;
import merchant_backend.entities.Users;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final merchant_backend.repository.UsersRepository userRepository;

	@Override
	public Users loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found: " + username));
	}
}