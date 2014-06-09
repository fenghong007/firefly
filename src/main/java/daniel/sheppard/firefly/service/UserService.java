package daniel.sheppard.firefly.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import daniel.sheppard.firefly.domain.User;
import daniel.sheppard.firefly.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;

	public Page<User> findAllUser() {
		return userRepository.findAll(new PageRequest(0, 10));
	}

	public void saveUser(User user) {
		userRepository.save(user);
	}

}
