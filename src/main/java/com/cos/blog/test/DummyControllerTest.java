package com.cos.blog.test;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@RestController
public class DummyControllerTest {
	 
	@Autowired
	private UserRepository userRepository;
	
	//한 페이지당 2건의 데이터를 리턴받아볼 예정
	@GetMapping("/dummy/user")
	public Page<User> pageList(@PageableDefault(size=2,sort="id",direction = Direction.DESC) Pageable pageable ){
		Page<User> users = userRepository.findAll(pageable);
		return users;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}

	
	//{id}주소로 파라미터를 전달받을 수 있음
	// localhost:8080/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// findById가 Optional<T>인 이유
		// {id}값을 데이터베이스에서 못찾아올 경우 null로 반환될텐데
		// 그럼 프로그램에 문제가 생김
		// 하여 Optional로 User객체를 감싸서 가져와서 null인지 아닌지 판단 후 return
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당유저는 없습니다.id:"+id);
			}
		});
		return user;
	}
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id:"+user.getId());
		System.out.println("username:"+ user.getUsername());
		System.out.println("password:"+ user.getPassword());
		System.out.println("email:"+ user.getEmail());
		System.out.println("role:"+ user.getRole());
		System.out.println("createDate:" + user.getCreateDate());
		 
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다."; 
	}
}
