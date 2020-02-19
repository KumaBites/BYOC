package com.kumabites.mm.moneymanagement;

import android.app.Application;
import android.os.AsyncTask;

import MMDAO.debtDao;
import MMDAO.userDao;
import MMENTITY.Debt;


public class MoneyRespository {

private debtDao mDebtDao;
private userDao mUserDao;

public void insert(Debt debt){
    new insertAsyncTask(mDebtDao).execute(debt);
    }

    MoneyRespository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mDebtDao = db.debtDao();
        mUserDao = db.userDao();
    }
    public static class insertAsyncTask extends AsyncTask<Debt,Void,Void>{
        private debtDao mAsyncTaskDao;

        insertAsyncTask(debtDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Debt... params){
            mAsyncTaskDao.insertNew(params[0]);
            return null;
        }

    }
    public static class UpdateAsyncTask extends AsyncTask<Debt,Void,Void>{
        private debtDao mAsyncTaskDao;

        UpdateAsyncTask(debtDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Debt... params){
            mAsyncTaskDao.updateDebt(params[0]);
            return null;
        }

    }
    public static class FindUserAsyncTask extends AsyncTask<String,Void,Void>{
        private userDao mAsyncTaskDao;

        FindUserAsyncTask(userDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params){
            mAsyncTaskDao.findUserSingle(params[0]);
            return null;
        }

    }

    /*zpublic static class FindUserAnyAsyncTask extends AsyncTask<Void,Void,List<User>>{
        private userDao mAsyncTaskDao;
        private List<User> rList;

        FindUserAnyAsyncTask(userDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<User> doInBackground(final Void... params){
            rList = mAsyncTaskDao.getAnyUser();
            return rList;
        }
        protected void onPostExecute(List<User> result){
           findAny = rList;
        }


    }

     */
}
