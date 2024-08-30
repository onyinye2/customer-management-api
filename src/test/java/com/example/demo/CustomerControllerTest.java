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
public class CustomerControllerTest {
    
    @Mock private CustomerService cs;
    @InjectMocks private CustomerController cc;

    @Test
    void testGetAllCustomers(){
        //given
        Customer c1 = new Customer("Sally Mae");
        Customer c2 = new Customer("Marie Dionne");
        //when
        given(cs.getCustomers()).willReturn(List.of(c1,c2));
        var cList = cc.getCustomers();
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
        given(cs.getById(2l)).willReturn(Optional.ofNullable(c2)); 
        //the return is Optional<Customer> so it should be nullable in case null returns
        var c = cc.getCustomerById(2l);
        //then
        assertThat(c).isNotNull();
        assertThat(c).isEqualTo(Optional.ofNullable(c2));

    }

    @Test
    void testCreateCustomer(){
        Customer c1 = new Customer("Sally Mae");
        
        //when
        given(cs.createCustomer(c1)).willReturn(c1); //the repository method and what it should return

        //the return is Optional<Customer> so it should be nullable in case null returns
        var c = cc.addCustomer(c1); //the service method calling the repo...
        //then
        assertThat(c).isNotNull();
        assertThat(c).isEqualTo(c1); //comparing service return vals with repo return val

    }
    @Test
    void testUpdateCustomer(){
        Customer c1 = new Customer("Sally Mae");
        Customer c2 = new Customer("Marie Dionne");

        Customer uc = new Customer("Celine Dionne");

        //when
        given(cs.updateCustomer(2l, "Celine Dionne")).willReturn(uc); //the repository method and what it should return

        var c = cc.updateCustomer(2l, "Celine Dionne"); //the service method calling the repo...
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
        
        given(cs.deleteCustomer(2l)).willReturn(true); 
        //the return is Optional<Customer> so it should be nullable in case null returns
        var rtn = cs.deleteCustomer(2l);
        var c = cs.getById(2l);
        //then
        assertThat(rtn).isEqualTo(true);
        assertThat(c).isEqualTo(Optional.empty());
        //assertThat(c).isEqualTo(c2);

    }

}
