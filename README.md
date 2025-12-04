# Biblioteca Maven

Questo è un progetto Java Maven per gestire una biblioteca. Permette di gestire **utenti**, **libri** e **prestiti**, utilizzando DAO e un servizio centrale per le operazioni.

## Tecnologie

- Java 
- Maven
- JDBC con MySQL
- Eclipse IDE (facoltativo)

## Struttura del progetto

- `com.biblioteca.model` → contiene le classi modello (`User`, `Book`, `Loan`)  
- `com.biblioteca.DAO` → contiene i DAO (`UserDAO`, `BookDAO`, `LoanDAO`)  
- `com.biblioteca.service` → logica di business (`LibraryService`)  
- `com.biblioteca.test` → classe `Main` per testare l'applicazione  

## Funzionalità

- Creazione, lettura, aggiornamento e cancellazione di **utenti** e **libri**  
- Creazione e gestione dei **prestiti**  
- Gestione dei **prestiti attivi** e delle **restituzioni**  
- Aggiornamento automatico della disponibilità dei libri  

