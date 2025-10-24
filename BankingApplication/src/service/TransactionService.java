package service;

import entity.User;
import repository.TransactionRepository;

import java.util.List;
import java.util.Map;

public class TransactionService {

    private TransactionRepository transactionRepository = new TransactionRepository();

//    public void printTransactions(){
//        transactionRepository.printTransactions();
//    }

    public User getUser(String userId){
        return transactionRepository.getUser(userId);
    }

    public boolean transferAmount(String userId, String payeeUserId, Double amount){
        return transactionRepository.transferAmount(userId, payeeUserId, amount);
    }

    public void printTransaction(String userId){
        transactionRepository.printTransaction(userId);
    }

    public void raiseChequeBookRequest(String userId){
        transactionRepository.raiseChequeBookRequest(userId);
    }

    public Map<String, Boolean> getAllCheckBookRequest(){
        return transactionRepository.getAllCheckBookRequest();
    }

    public List<String> getUserIdForCheckBookRequest(){
        return transactionRepository.getUserIdForCheckBookRequest();
    }

    public void approveCheckBookRequest(String userId){
        transactionRepository.approveCheckBookRequest(userId);
    }

}
