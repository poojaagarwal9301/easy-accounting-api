package com.api.expense.service;

import com.api.expense.dto.*;
import com.api.expense.exception.custom.IncorrectDateException;
import com.api.expense.exception.custom.UniqueKeyException;
import com.api.expense.model.ExpDetail;
import com.api.expense.model.ExpItemTbl;
import com.api.expense.model.UserExpTbl;
import com.api.expense.model.UsrTbl;
import com.api.expense.repository.ExpenseDetailsRepository;
import com.api.expense.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class UserSevice {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserSevice.class);

    private String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private SimpleDateFormat sdfSource = new SimpleDateFormat(DATE_FORMAT);
    DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Optional<List<UserDetailsDTO>> getUsers() {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<UsrTbl> usrDetails = userRepository.findAll();
        logger.info("getUsers data is " + usrDetails);
        List<UserDetailsDTO> userDetailsDTOs = null;
        AtomicInteger counter = new AtomicInteger(1);
        if (usrDetails.size() > 0) {
            userDetailsDTOs = usrDetails.stream().map(usrDetail ->
                            new UserDetailsDTO( usrDetail.getDob(), usrDetail.getUserId(),
                                    usrDetail.getFirstName() +
                                            (StringUtils.isNotEmpty(usrDetail.getMiddleName()) ? " " + usrDetail.getMiddleName() : "")
                                            + " " + usrDetail.getLastName(),
                                    usrDetail.getUserName(), ""))
                    .filter(Objects::nonNull).collect(Collectors.toList());
        }
        return Optional.ofNullable(userDetailsDTOs);

    }

    public Optional<List<UserDetailsDTO>> getUserWithRoles() {
        List<UserDetailsDTO> usrDetail = userRepository.getUserWithRole();
        return Optional.of(usrDetail);

    }

    public Optional<UsrTbl> addUser(UserRequestDTO userRequestDTO) {
        UsrTbl usrTbl = null;
        try {
            logger.info("data for add user " + userRequestDTO);
            usrTbl = mapUserData(userRequestDTO);
            usrTbl = userRepository.save(usrTbl);

        } catch (ParseException e) {
            logger.error(e.getMessage());
            throw new IncorrectDateException("Date cannot be parsed");
        } catch (ConstraintViolationException | DataIntegrityViolationException e) {
            logger.error(e.getMessage());
            throw new UniqueKeyException(e.getCause().toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return Optional.of(usrTbl);

    }

    UsrTbl mapUserData(UserRequestDTO userRequestDTO) throws ParseException {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UsrTbl usrTbl = new UsrTbl();
        usrTbl.setFirstName(userRequestDTO.getFirstName());
        usrTbl.setLastName(userRequestDTO.getLastName());
        usrTbl.setDob(Date.valueOf(targetFormat.format(sdfSource.parse(userRequestDTO.getDob()))));
        usrTbl.setCity(userRequestDTO.getCity());
        usrTbl.setContact_number(userRequestDTO.getContact_number());
        usrTbl.setEmail(userRequestDTO.getEmail());
        usrTbl.setUserName(userRequestDTO.getUserName());
        usrTbl.setPassword(userRequestDTO.getPassword());

        return usrTbl;
    }
}
