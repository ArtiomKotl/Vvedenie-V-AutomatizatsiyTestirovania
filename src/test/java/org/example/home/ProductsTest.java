package org.example.home;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import javax.persistence.PersistenceException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductsTest extends AbstractTest {


    @Test
    @Order(1)
    void getAllValidProducts() throws SQLException {
        //given
        String sql = "SELECT * FROM products";
        Statement stmt = getConnection().createStatement();
        int countTableSize = 0;
        //when
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            countTableSize++;
        }
        //then
        Assertions.assertEquals(10, countTableSize);
    }



    @Test
    @Order(2)
    void getOnlyRollProducts() {
        //given
        String sql = "SELECT * FROM products WHERE menu_name LIKE '%ROLL%'";
        //when
        final Query query = getSession().createSQLQuery(sql).addEntity(ProductsEntity.class);
        //then
        Assertions.assertEquals(3, query.list().size());
    }

    @Test
    @Order(3)
    void getOnlyProductsMoreExpensiveThan400() {
        //given
        String sql = "SELECT * FROM products WHERE price > 400";
        //when
        final Query query;
        query = getSession().createSQLQuery(sql).addEntity(ProductsEntity.class);
        //then
        Assertions.assertEquals(5, query.list().size());
    }

    @ParameterizedTest
    @Order(4)
    @CsvSource({"UNAGI MAKI, 550.0", "ORANGE JUICE, 75.0", "PANCAKE, 150.0"})
    void addNewProduct(String product, String price) {
        //given
        Session session = getSession();
        String sql = "SELECT MAX(product_id) FROM products";
        final Query maxid = session.createSQLQuery(sql);
        Integer nextid = (Integer) maxid.uniqueResult() + 1;

        ProductsEntity productsEntity = new ProductsEntity();
        productsEntity.setProductId(nextid.shortValue());
        productsEntity.setMenuName(product);
        productsEntity.setPrice(price);

        session.beginTransaction();
        session.persist(productsEntity);
        session.getTransaction().commit();
        //when
        final Query allProducts = session.createQuery("FROM ProductsEntity");

        final Query newProduct = session.createSQLQuery("SELECT * FROM products WHERE product_id=" + nextid)
                .addEntity(ProductsEntity.class);
        Optional<ProductsEntity> queryEntity = newProduct.uniqueResultOptional();
        //then
        Assertions.assertEquals(nextid, allProducts.list().size());

        Assertions.assertTrue(queryEntity.isPresent());
        Assertions.assertEquals(product, queryEntity.get().getMenuName());
        Assertions.assertEquals(price, queryEntity.get().getPrice());
    }

    @Test
    @Order(5)
    void deleteNewProduct() {
        //given
        Session session = getSession();
        final Query queryProducts = getSession()
                .createSQLQuery("SELECT * FROM products WHERE product_id>" + 10).addEntity(ProductsEntity.class);
        List<ProductsEntity> entityForDelete = queryProducts.getResultList();
        Assumptions.assumeFalse(entityForDelete.isEmpty());
        //when
        for (ProductsEntity productsEntity : entityForDelete) {
            session.beginTransaction();
            session.delete(productsEntity);
            session.getTransaction().commit();
        }
        //then
        final Query queryAfterDelete = getSession()
                .createSQLQuery("SELECT * FROM products WHERE product_id>" + 10).addEntity(ProductsEntity.class);
        List<ProductsEntity> entity = queryAfterDelete.getResultList();
        Assertions.assertEquals(0, entity.size());
    }

    @Test
    @Order(6)
    void addNotExistProduct() {
        //given
        ProductsEntity productsEntity = new ProductsEntity();
        //when
        Session session = getSession();
        session.beginTransaction();
        session.persist(productsEntity);
        //then
        Assertions.assertThrows(PersistenceException.class, () -> session.getTransaction().commit());
    }
}