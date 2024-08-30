package com.example.demo;

import static org.mockito.Mockito.when;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    
    @Mock private CustomerRepository cr;
    @InjectMocks private CustomerService cs;

    @Test
    void testGetAllCustomers(){
        //given
        Customer c1 = new Customer("Sally Mae");
        Customer c2 = new Customer("Marie Dionne");
        //when
        given(cr.findAll()).willReturn(List.of(c1,c2));
        var cList = cs.getCustomers();
        //then
        assertThat(cList).isNotNull();
        assertThat(cList.size()).isEqualTo(2);


    }

    @Test
    void testGetCustomerById(){
        Customer c1 = new Customer("Sally Mae");
        Customer c2 = new Customer("Marie Dionne");
        Customer c3 = new Customer("Elizabeth Anne");
        //when
        given(cr.findById(2l)).willReturn(Optional.ofNullable(c2)); 
        //the return is Optional<Customer> so it should be nullable in case null returns
        var c = cs.getById(2l);
        //then
        assertThat(c).isNotNull();
        assertThat(c).isEqualTo(Optional.ofNullable(c2));

    }

    @Test
    void testCreateCustomer(){
        Customer c1 = new Customer("Sally Mae");
        
        //when
        given(cr.save(c1)).willReturn(c1); //the repository method and what it should return

        //the return is Optional<Customer> so it should be nullable in case null returns
        var c = cs.createCustomer(c1); //the service method calling the repo...
        //then
        assertThat(c).isNotNull();
        assertThat(c).isEqualTo(c1); //comparing service return vals with repo return val

    }
    @Test
    void testUpdateCustomer(){
        Customer c1 = new Customer("Sally Mae");
        Customer c2 = new Customer("Marie Dionne");
        //Customer c3 = new Customer("Elizabeth Anne");
        
        //when
        given(cr.findById(2l)).willReturn(Optional.ofNullable(c2)); 
        given(cr.save(c2)).willReturn(c2); //the repository method and what it should return

        var c = cs.updateCustomer(2l, "Celine Dionne"); //the service method calling the repo...
        //then
        assertThat(c).isNotNull();
        assertThat(c.getName()).isNotEqualTo("Marie Dionne");
        assertThat(c.getName()).isEqualTo("Celine Dionne"); //comparing service return vals with repo return val

    }

    @Test
    void testDeleteById(){
        Customer c1 = new Customer("Sally Mae");
        Customer c2 = new Customer("Marie Dionne");
        Customer c3 = new Customer("Elizabeth Anne");
        //when
        
        given(cr.findById(2l)).willReturn(null); 
        //the return is Optional<Customer> so it should be nullable in case null returns
        cs.deleteCustomer(2l);
        var c = cs.getById(2l);
        //then
        assertThat(c).isNull();
        //assertThat(c).isEqualTo(c2);

    }
    
    
}
