package com.sysensor.app.repository;

import com.sysensor.app.TestConst;
import com.sysensor.app.model.Bus;
import com.sysensor.app.model.BusOwner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BusRepositoryTest {
    @Autowired
    BusRepo busRepo;
    @Autowired
    BusOwnerRepo busOwnerRepo;

    @Test
    @Transactional
    public void busShouldReturnTheCorrectAttributes() {
        Optional<Bus> busOwnerOptional = busRepo.findById(TestConst.BUS_ONE_UUID);
        Assert.assertTrue(busOwnerOptional.isPresent());

        Bus bus = busOwnerOptional.get();
        Assert.assertEquals(TestConst.BUS_ONE_UUID, bus.getUuid());
        Assert.assertEquals("EY3456", bus.getRegistration_no());

        BusOwner busOwner = bus.getBusOwner();
        Assert.assertEquals(TestConst.BUS_OWNER_THREE_UUID, busOwner.getUuid());
        Assert.assertEquals("Selvam", busOwner.getName());
        Assert.assertEquals("Matara", busOwner.getAddress());
        Assert.assertEquals("0793005675", busOwner.getPhone());
        Assert.assertEquals("selvam", busOwner.getUsername());
        Assert.assertEquals("$2a$10$5SM3OIksgYLL6LU8bb7Raeff2A1nwAuEsF.XoXQq6QxvJwRjh96Jq", busOwner.getPassword());

    }

    @Test
    @Transactional
    public void busShouldCreatedAndAttachedToTheBusOwner() {
        Optional<BusOwner> busOwnerOptional = busOwnerRepo.findById(TestConst.BUS_OWNER_THREE_UUID);

        Bus bus = new Bus();
        bus.setRegistration_no("MK2347");
        bus.setBusOwner(busOwnerOptional.get());

        busRepo.save(bus);
        Optional<Bus> busOptional = busRepo.findById(bus.getUuid());
        Assert.assertTrue(busOptional.isPresent());

        Bus busAfterSave = busOptional.get();
        Assert.assertEquals(bus.getUuid(), busAfterSave.getUuid());
        Assert.assertEquals("MK2347", busAfterSave.getRegistration_no());

        BusOwner busOwnerAfterSave = busAfterSave.getBusOwner();
        Assert.assertEquals(TestConst.BUS_OWNER_THREE_UUID, busOwnerAfterSave.getUuid());
        Assert.assertEquals("Selvam", busOwnerAfterSave.getName());
        Assert.assertEquals("Matara", busOwnerAfterSave.getAddress());
        Assert.assertEquals("0793005675", busOwnerAfterSave.getPhone());
        Assert.assertEquals("selvam", busOwnerAfterSave.getUsername());
        Assert.assertEquals("$2a$10$5SM3OIksgYLL6LU8bb7Raeff2A1nwAuEsF.XoXQq6QxvJwRjh96Jq", busOwnerAfterSave.getPassword());
    }

}
