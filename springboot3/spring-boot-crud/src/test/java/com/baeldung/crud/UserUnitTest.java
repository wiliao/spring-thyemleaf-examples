package com.baeldung.crud;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.baeldung.crud.entities.MyUser;

public class UserUnitTest {
    
    @Test
    public void whenCalledGetName_thenCorrect() {
    	MyUser user = new MyUser("Julie", "julie@domain.com");
        
        assertThat(user.getName()).isEqualTo("Julie");
    }
    
    @Test
    public void whenCalledGetEmail_thenCorrect() {
    	MyUser user = new MyUser("Julie", "julie@domain.com");
        
        assertThat(user.getEmail()).isEqualTo("julie@domain.com");
    }
    
    @Test
    public void whenCalledSetName_thenCorrect() {
    	MyUser user = new MyUser("Julie", "julie@domain.com");
        
        user.setName("John");
        
        assertThat(user.getName()).isEqualTo("John");
    }
    
    @Test
    public void whenCalledSetEmail_thenCorrect() {
    	MyUser user = new MyUser("Julie", "julie@domain.com");
        
        user.setEmail("john@domain.com");
        
        assertThat(user.getEmail()).isEqualTo("john@domain.com");
    }
    
    @Test
    public void whenCalledtoString_thenCorrect() {
    	MyUser user = new MyUser("Julie", "julie@domain.com");
        assertThat(user.toString()).isEqualTo("User{id=0, name=Julie, email=julie@domain.com}");
    }
}
