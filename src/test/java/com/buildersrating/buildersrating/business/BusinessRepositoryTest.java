package com.buildersrating.buildersrating.business;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class BusinessRepositoryTest {

    @Autowired
    BusinessRepository businessRepositoryTest;

    @AfterEach
    void tearDown(){
        businessRepositoryTest.deleteAll();
    }

    @Disabled
    @Test
    void existsByRegNumber() {
        //given

        //when

        //then
    }

    @Test
    void checkWhenThereAreActiveBusiness() {
        //given
        Business businessTest1=new Business("Thabizolo",1,"648HYT","1342 Edendale","Active",LocalDateTime.now(),1,0,0);
        businessRepositoryTest.save(businessTest1);
        Business businessTest2=new Business("Sizabantu",1,"HR8965","1456 Monti","Active",LocalDateTime.now(),1,1,0);
        businessRepositoryTest.save(businessTest2);
        Business businessTest3=new Business("Siyakha",1,"FIJR7985","1894 Toti","Active",LocalDateTime.now(),1,0,1);
        businessRepositoryTest.save(businessTest3);

        //when
        List<Business> businessCheck=businessRepositoryTest.findAllActive();

        //then
        assertThat(businessCheck).isNotEmpty();
    }

    @Test
    void checkWhenThereArentActiveBusiness() {
        //given
        Business businessTest2=new Business("Sizabantu",1,"HR8965","1456 Monti","Active",LocalDateTime.now(),1,1,0);
        businessRepositoryTest.save(businessTest2);
        Business businessTest3=new Business("Siyakha",1,"FIJR7985","1894 Toti","Active",LocalDateTime.now(),1,0,1);
        businessRepositoryTest.save(businessTest3);

        //when
        List<Business> businessCheck=businessRepositoryTest.findAllActive();

        //then
        assertThat(businessCheck).isEmpty();
    }

    @Test
    void checkWhenThereAreInactiveBusiness() {
        //given
        Business businessTest1=new Business("Thabizolo",1,"648HYT","1342 Edendale","Active",LocalDateTime.now(),1,0,0);
        businessRepositoryTest.save(businessTest1);
        Business businessTest2=new Business("Sizabantu",1,"HR8965","1456 Monti","Active",LocalDateTime.now(),1,1,0);
        businessRepositoryTest.save(businessTest2);
        Business businessTest3=new Business("Siyakha",1,"FIJR7985","1894 Toti","Active",LocalDateTime.now(),1,0,1);
        businessRepositoryTest.save(businessTest3);

        //when
        List<Business> businessCheck=businessRepositoryTest.findAllInactive();

        //then
        assertThat(businessCheck).isNotEmpty();
    }

    @Test
    void checkWhenThereArentInactiveBusiness() {
        //given
        Business businessTest1=new Business("Thabizolo",1,"648HYT","1342 Edendale","Active",LocalDateTime.now(),1,0,0);
        businessRepositoryTest.save(businessTest1);

        //when
        List<Business> businessCheck=businessRepositoryTest.findAllInactive();

        //then
        assertThat(businessCheck).isEmpty();
    }

    @Test
    void checkWhenThereAreDeletedBusiness() {
        //given
        Business businessTest2=new Business("Sizabantu",1,"HR8965","1456 Monti","Active",LocalDateTime.now(),1,1,0);
        businessRepositoryTest.save(businessTest2);

        //when
        List<Business> businessCheck=businessRepositoryTest.findAllDeleted();

        //then
        assertThat(businessCheck).isNotEmpty();
    }

    @Test
    void checkWhenThereArentDeletedBusiness() {
        //given
        Business businessTest1=new Business("Thabizolo",1,"648HYT","1342 Edendale","Active",LocalDateTime.now(),1,0,0);
        businessRepositoryTest.save(businessTest1);
        Business businessTest3=new Business("Siyakha",1,"FIJR7985","1894 Toti","Active",LocalDateTime.now(),1,0,1);
        businessRepositoryTest.save(businessTest3);

        //when
        List<Business> businessCheck=businessRepositoryTest.findAllDeleted();

        //then
        assertThat(businessCheck).isEmpty();
    }

    @Test
    void checkWhenThereAreSuspendedBusiness() {
        //given
        Business businessTest3=new Business("Siyakha",1,"FIJR7985","1894 Toti","Active",LocalDateTime.now(),1,0,1);
        businessRepositoryTest.save(businessTest3);

        //when
        List<Business> businessCheck=businessRepositoryTest.findAllSuspended();

        //then
        assertThat(businessCheck).isNotEmpty();
    }

    @Test
    void checkWhenThereArentSuspendedBusiness() {
        //given
        Business businessTest1=new Business("Thabizolo",1,"648HYT","1342 Edendale","Active",LocalDateTime.now(),1,0,0);
        businessRepositoryTest.save(businessTest1);
        Business businessTest2=new Business("Sizabantu",1,"HR8965","1456 Monti","Active",LocalDateTime.now(),1,1,0);
        businessRepositoryTest.save(businessTest2);

        //when
        List<Business> businessCheck=businessRepositoryTest.findAllSuspended();

        //then
        assertThat(businessCheck).isEmpty();
    }
}