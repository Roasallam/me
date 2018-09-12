package com.training.server.work.authentication;

import com.training.server.work.DB.daoImplemnters.UserDAOImp;
import com.training.server.work.Status;
import com.training.server.work.entity.User;

/**
 * verifies if a user is a user of the system
 * and checks for users passwords if they are correct
 * or not
 */

public class Verification {

   private static UserDAOImp userDAOImp = new UserDAOImp();

   /**
    * checks for the password status,
    * if a user is not exist then return NOT EXIST
    * if the password which entered by the user is not equal to
    * the password which saved in the database then
    * return INCORRECT PASSWORD and the user is not able to sign in
    * if not then he is able to sign in
    * @param userName userName specified for the user
    * @param password the password that the user entered
    * @return not exist if the user is not a user of the system, WELCOME if and only if
    * the password matched the saved one
    */

    private static Status passwordStatus (String userName, String password) {

      User user = userDAOImp.findByName(userName);

      if (user == null)
         return Status.NOT_EXIST;

      String savedPassword = user.getPassword();

      if (savedPassword.equals(password))
         return Status.WELCOME;

      return Status.INCORRECT_PASSWORD;
   }

   /**
    * checks if the password entered by the user is valid or not
    * @param userName userName specified for the user
    * @param password the password that the user entered
    * @return {@code true} if and only if the password is valid, {@code false}
    * otherwise
    */

   public static boolean isValidPassword (String userName, String password) {

       Status status = passwordStatus(userName, password);

       if (status == Status.WELCOME)
          return true;

       return false;
   }
}