package org.example;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        SessionFactory sessionFactory = new
                Configuration().configure().buildSessionFactory();
        Session session =sessionFactory.openSession();
        if(session!=null){
            System.out.println("Sessió Oberta amb èxit!!!!");
        }
        else{
            System.out.println("Error al obrir la sessió");
        }
        int cont=0;
        while(cont==0){
            muestraMenuOpciones();
            int selecciona=sc.nextInt();
            switch (selecciona){
                case 1:
                    rellenaLibro(session);
                    break;

                case 2:
                    readLlibres(session);
                    break;

                case 3:
                    deleteaFunction(session);
                    break;

                case 4:
                    updatearTabla(session);
                    break;
                case 5:
                    cont=1;

            }
        }
        System.out.println("Has terminado");









// aci van les operacions CRUD sobre les entitats
// Tancar sessió i Factory
        session.close();
        sessionFactory.close();
    }

    public static void updatearTabla(Session session){
        Scanner sc=new Scanner(System.in);
        System.out.println("Dime un ID para buscar");
        int id=sc.nextInt();
        System.out.println("Dime un nuevo Título");
        String tutolNou=sc.next();
        updateLlibre(session,id,tutolNou);


    }

    public static void deleteaFunction(Session session){
        Scanner sc=new Scanner(System.in);
        System.out.println("Dime un id para eliminar un libro");
        int id=sc.nextInt();
        deleteLlibre(session,id);

    }

    public static void rellenaLibro(Session session){
        Scanner sc=new Scanner(System.in);
        System.out.println("Dime el ID");
        int id= sc.nextInt();
        System.out.println("Dime el título");
        String titol=sc.next();
        System.out.println(" ");
        System.out.println("Dime el Autor");
        String autor=sc.next();

        createLlibre(session,id,titol,autor);


    }


    public static void muestraMenuOpciones(){
        System.out.println("************MENÚ LIBROS*************");
        System.out.println(" ");
        System.out.println("1. Crea un objecte");
        System.out.println("2. Lee los libros");
        System.out.println("3. Deletea un libro");
        System.out.println("4. Modifica un libro");
        System.out.println("5. Sal del Programa");
    }



    public static void createLlibre(Session session, int id, String titol,
                                    String autor) {
// Iniciem una transacció
        session.beginTransaction();
// Creem una nova instància de LlibresPojo
        LibrosEntity nouLlibre = new LibrosEntity();
        nouLlibre.setId(id);
        nouLlibre.setTitol(titol);
        nouLlibre.setAutor(autor);
// Emmagatzemem l'entitat a la base de dades
        session.save(nouLlibre);
// Confirmem la transacció
        session.getTransaction().commit();
    }


    public static void readLlibres(Session session) {
// Obtenim tots els llibres de la base de dades
        List<LibrosEntity> llibres = session.createQuery("from LibrosEntity ",
                LibrosEntity.class).list();
// Mostrem els resultats
        System.out.println("Llibres a la base de dades:");
        for (LibrosEntity llibre : llibres) {
            System.out.println("ID: " + llibre.getId() + ", Títol: " +
                    llibre.getTitol() + ", Autor: " + llibre.getAutor());
        }
    }

    public static void deleteLlibre(Session session, int id) {
// Iniciem una transacció
        session.beginTransaction();
// Obtenim el llibre amb l'ID proporcionat
        LibrosEntity llibre = session.get(LibrosEntity.class, id);
// Esborrem el llibre
        session.delete(llibre);
// Confirmem la transacció
        session.getTransaction().commit();
    }

    public static void updateLlibre(Session session, int id, String nouTitol)
    {
// Iniciem una transacció
        session.beginTransaction();
// Obtenim el llibre amb l'ID proporcionat
        LibrosEntity llibre = session.get(LibrosEntity.class, id);
// Actualitzem el títol del llibre
        llibre.setTitol(nouTitol);

        session.getTransaction().commit();
    }
}


