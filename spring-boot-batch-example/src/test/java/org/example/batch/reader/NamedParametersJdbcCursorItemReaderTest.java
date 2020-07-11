package org.example.batch.reader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.support.ListPreparedStatementSetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = NamedParametersJdbcCursorItemReaderTest.TestConfiguration.class)
public class NamedParametersJdbcCursorItemReaderTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JdbcCursorItemReader<Person> itemReader;

    @Before
    public void setUp() {
        jdbcTemplate.execute("create table person (id int primary key, name varchar(20));");
        jdbcTemplate.execute("insert into person values (1, 'foo');");
        jdbcTemplate.execute("insert into person values (2, 'bar');");
        itemReader.open(new ExecutionContext());
    }

    @Test
    public void testReadDataWithNamedParameters() throws Exception {
        Person person = itemReader.read();
        Assert.assertNotNull(person);
        Assert.assertEquals(1, person.getId());
        Assert.assertEquals("foo", person.getName());
        person = itemReader.read();
        Assert.assertNull(person);
    }

    @Configuration
    static class TestConfiguration {

        @Bean
        public JdbcCursorItemReader<Person> itemReader() {
            String sql = "select * from person where id = :id and name = :name";
            Map<String, Object> namedParameters = new HashMap<String, Object>() {{
                put("id", 1);
                put("name", "foo");
            }};
            return new JdbcCursorItemReaderBuilder<Person>()
                    .name("personItemReader")
                    .dataSource(dataSource())
                    .rowMapper(new BeanPropertyRowMapper<>(Person.class))
                    .sql(NamedParameterUtils.substituteNamedParameters(sql, new MapSqlParameterSource(namedParameters)))
                    .preparedStatementSetter(new ListPreparedStatementSetter(Arrays.asList(NamedParameterUtils.buildValueArray(sql, namedParameters))))
                    .build();
        }

        @Bean
        public DataSource dataSource() {
            return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .addScript("/org/springframework/batch/core/schema-drop-h2.sql")
                    .addScript("/org/springframework/batch/core/schema-h2.sql")
                    .build();
        }

        @Bean
        public JdbcTemplate jdbcTemplate() {
            return new JdbcTemplate(dataSource());
        }

    }

    public static class Person {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}