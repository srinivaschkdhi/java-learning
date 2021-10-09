package multithreading.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserServiceWithThreadLocal {
    public static ThreadLocal<SimpleDateFormat> dateFormatter = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");// Called once for each thread
        }

        @Override
        public SimpleDateFormat get() {
            return super.get();//1st call = initialValue() Subsequent calls  return initialized value
        }
    };
}

class UserService2 {
    public String birthDate(int userId) {
        Date birthDate = birthDateFromDB(userId);
        SimpleDateFormat df = UserServiceWithThreadLocal.dateFormatter.get();// each thread will get its own copy
        return df.format(birthDate);
    }

    Date birthDateFromDB(int userId) {
        return new Date();
    }
}

class UserServiceWithThreadLocal8 {
    public static ThreadLocal<SimpleDateFormat> df = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
}