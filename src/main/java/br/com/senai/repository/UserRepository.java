package br.com.senai.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.senai.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
