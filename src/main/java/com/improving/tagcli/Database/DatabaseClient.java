package com.improving.tagcli.Database;

import com.improving.tagcli.models.Emote;
import com.improving.tagcli.models.Weapon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class DatabaseClient {
    //autowire: where to put the value ; check the register in Spring; if not, it will crash;
    //@Autowired
    private static final Logger logger =
            LoggerFactory.getLogger(DatabaseClient.class);

    private final JdbcTemplate jdbcTemplate;

    public DatabaseClient(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Weapon> readDataFromTable(){
        try{
            List <Weapon> weapons =jdbcTemplate.query("SELECT * FROM weapon LIMIT 10",
                    (rs, rowNum) ->
                            new Weapon (rs.getInt("Id"),
                                    rs.getString("Name"),
                                    rs.getString("area"),
                                    rs.getString("itemType")));
            weapons.forEach(weapon->logger.info("Weapon ID: {}, Name:{}",weapon.getId(),weapon.getName()));
            return weapons;
        }catch(DataAccessException e) {
            logger.error("Error: ", e);
        }
        return Collections.emptyList();
    }

    public void insertEmoteIntoTable(Emote emote) {


        try {
            logger.info("Got it!");
            int rowsAffected = jdbcTemplate.update("INSERT INTO emote (prompt, text)" +
                                    " Values " +  "(" + "'" + emote.getPrompt() + "'"  + ", '" + emote.getText()+ "'" + ")");

            logger.info("Rows affected:{}", rowsAffected);
        } catch (DataAccessException e) {
            logger.error("Exception throw in JDBC", e);
        }
    }
}
