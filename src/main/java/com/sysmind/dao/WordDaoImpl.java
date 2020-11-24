package com.sysmind.dao;

import com.sysmind.model.WordEntity;
import com.sysmind.model.WordEntityListResponse;
import com.sysmind.model.WordEntityResponse;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class WordDaoImpl  implements WordDao{

    static SessionFactory sessionFactoryObj;

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configObj = new Configuration();
            configObj.configure("hibernate.cfg.xml");
            configObj.addAnnotatedClass(com.sysmind.model.WordEntity.class);
            configObj.configure();
            ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();
            sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
        }catch(SessionException sessionException){
            sessionException.printStackTrace();
        }
        return sessionFactoryObj;
    }

    @Override
    public WordEntityResponse saveWord(String word) {

        WordEntityResponse response = new WordEntityResponse();
        Session sessionObj = null;
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();
            WordEntity wordEntity = new WordEntity();
            wordEntity.setWord(word);
            sessionObj.save(wordEntity);
            sessionObj.getTransaction().commit();
            response.setResult(true);

        }catch(NullPointerException nullPointerException){
            response.validationResult.addError(nullPointerException.getMessage(), "DB ERROR");

        }catch(Exception sqlException){
            if(sessionObj!=null && sessionObj.getTransaction()!=null) {
                sessionObj.getTransaction().rollback();
            }
            response.validationResult .addError(sqlException.getMessage(), "DB ERROR");
        }
        finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        return response;
    }

    @Override
    public WordEntityListResponse getWords() {
        WordEntityListResponse response = new WordEntityListResponse();
        Session sessionObj = null;
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();
            List<WordEntity> list = sessionObj.createCriteria(WordEntity.class).list();
            response.setResult(list);
            sessionObj.getTransaction().commit();

        }catch(NullPointerException nullPointerException){
            response.validationResult.addError(nullPointerException.getMessage(), "DB ERROR");

        }catch(Exception sqlException){
            if(sessionObj!=null && sessionObj.getTransaction()!=null) {
                sessionObj.getTransaction().rollback();
            }
            response.validationResult .addError(sqlException.getMessage(), "DB ERROR");
        }
        finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        return response;
    }
}
