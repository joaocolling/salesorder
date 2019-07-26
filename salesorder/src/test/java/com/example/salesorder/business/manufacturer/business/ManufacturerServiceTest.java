/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.business.manufacturer.business;

import com.example.salesorder.business.manufacturer.domain.Manufacturer;
import com.example.salesorder.business.manufacturer.dto.ManufacturerDTO;
import javax.transaction.Transactional;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author joao
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
public class ManufacturerServiceTest {

    @Autowired
    private ManufacturerService manufacturerService;

    public ManufacturerServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    @Transactional
    public void testGetAndValidated() {
        ManufacturerDTO manufacturerDTO = buildManufacturerDTO();
        Integer idManufacturer = manufacturerService.insert(manufacturerDTO);

        try {
            Manufacturer manufacturer = manufacturerService.getAndValidated(idManufacturer);
            assertThat(manufacturer, notNullValue());
        } catch (RuntimeException e) {
            fail("Não deveria causar exceção.");
        }

        try {
            manufacturerService.getAndValidated(404);
            fail("Não deveria seguir execução!");
        } catch (RuntimeException e) {
            assertThat(e.getMessage(), is("Fabricante não encontrado."));
        }

    }

    @Test
    @Transactional
    public void testInsertAndGet() {

        ManufacturerDTO manufacturerDTO = buildManufacturerDTO();
        Integer idManufacturer = manufacturerService.insert(manufacturerDTO);
        assertNotNull(idManufacturer);

        Manufacturer manufacturer = manufacturerService.get(idManufacturer);
        assertThat(manufacturer, notNullValue());
        assertThat(manufacturer.getName(), is(manufacturerDTO.getName()));
        assertThat(manufacturer.getCreated(), is(manufacturer.getUpdated()));

    }

    @Test
    @Transactional
    public void testInsertNullAndEmptyNames() {
        try {
            ManufacturerDTO manufacturerDTO = buildManufacturerNullName();

            manufacturerService.insert(manufacturerDTO);
            fail("Não deveria seguir execução!");
        } catch (RuntimeException e) {
            assertThat(e.getMessage(), is("Nome do Fabricante não pode ser vazio."));
        }

        try {
            ManufacturerDTO manufacturerDTO = buildManufacturerEmptyName();

            manufacturerService.insert(manufacturerDTO);
            fail("Não deveria seguir execução!");
        } catch (RuntimeException e) {
            assertThat(e.getMessage(), is("Nome do Fabricante não pode ser vazio."));
        }
    }

    @Test
    public void testUpdate() {
        ManufacturerDTO manufacturerDTO = buildManufacturerDTO();
        Integer idManufacturer = manufacturerService.insert(manufacturerDTO);

        ManufacturerDTO manufacturerDTO2 = buildManufacturerDTO2();
        manufacturerService.update(idManufacturer, manufacturerDTO2);

        Manufacturer manufacturer = manufacturerService.get(idManufacturer);
        assertThat(manufacturer.getName(), not(manufacturerDTO.getName()));
        assertThat(manufacturer.getName(), is(manufacturerDTO2.getName()));
        assertThat(manufacturer.getCreated(), not(manufacturer.getUpdated()));
    }

    @Test
    @Transactional
    public void testUpdateNullAndEmptyNames() {
        ManufacturerDTO manufacturerDTO = buildManufacturerDTO();
        Integer idManufacturer = manufacturerService.insert(manufacturerDTO);

        try {
            manufacturerDTO
                    = buildManufacturerNullName();
            manufacturerService.update(idManufacturer, manufacturerDTO);
            fail("Não deveria seguir execução!");
        } catch (RuntimeException e) {
            assertThat(e.getMessage(), is("Nome do Fabricante não pode ser vazio."));
        }

        try {
            manufacturerDTO
                    = buildManufacturerEmptyName();
            manufacturerService.update(idManufacturer, manufacturerDTO);
            fail("Não deveria seguir execução!");
        } catch (RuntimeException e) {
            assertThat(e.getMessage(), is("Nome do Fabricante não pode ser vazio."));
        }
    }

    @Test
    public void testDelete() {
        ManufacturerDTO manufacturerDTO = buildManufacturerDTO();
        Integer idManufacturer = manufacturerService.insert(manufacturerDTO);

        Manufacturer manufacturer = manufacturerService.get(idManufacturer);
        assertThat(manufacturer, notNullValue());

        manufacturerService.delete(idManufacturer);
        /**
         * @TODO Flush Entity Manager manufacturer =
         * manufacturerService.get(idManufacturer); assertThat(manufacturer,
         * nullValue());
         *
         */
    }

    private ManufacturerDTO buildManufacturerEmptyName() {
        return new ManufacturerDTOBuilder()
                .withName("").build();
    }

    private ManufacturerDTO buildManufacturerNullName() {
        return new ManufacturerDTOBuilder()
                .withName(null).build();
    }

    private ManufacturerDTO buildManufacturerDTO2() {
        return new ManufacturerDTOBuilder()
                .withName("Brocks II").build();
    }

    private ManufacturerDTO buildManufacturerDTO() {
        return new ManufacturerDTOBuilder()
                .withName("Brocks").build();
    }
}
