import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by Khartonov Oleg on 22.05.2016.
 */
@Component
public class DBHelper {
    private final String SQL_INSERT_ROWS = "insert into register_txns (id, register_date, register_id, register_type, txn_id, pay_id, operation, operation_date, status, amount, fee, annul_amount, phone, goods_id ,category_id, compensation_operation, trading_concession, branch, source_payment)\n" +
            "values (1,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String SQL_ADD_REGISTER = "insert into register (register_id, day_date) values (?,?)";
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    @Autowired
    public DBHelper(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void getInsertRegister(TransactionInfo txns) throws SQLException {
        try {
            jdbcTemplate.update(SQL_ADD_REGISTER, new Object[]{txns.getRegisterId(), txns.getTimeStamp()});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getInsertTxns(TransactionInfo.Transactions txns, Timestamp registerDate, long id, long operation, int type) throws SQLException {
        try {
            jdbcTemplate.update(SQL_INSERT_ROWS, new Object[] {registerDate, id, type, txns.getPctransId(), txns.getPayId(), operation, txns.getDate(), txns.getStatus(), txns.getAmount(), txns.getFee(), txns.getAnnulAmount(), txns.getPhone(), txns.getGoodsId(), txns.getCategoryId(),
                    txns.getCompensationOperator(), txns.getTradingConcession(), txns.getBranch(), txns.getSourcePayment()});
        } catch (Exception e) {
            throw new IllegalArgumentException("SQL script was failed: " + txns.getPctransId(), e);
        }
    }

    public void addTxns(final TransactionInfo transactionInfo) throws SQLException {
        getInsertRegister(transactionInfo);
        try {
            if (transactionInfo.getTransactions() != null) {
                transactionInfo.getTransactions()
                        .forEach(t -> {
                            long operation = Constants.PAY;
                            try {
                                getInsertTxns(t, transactionInfo.getTimeStamp(), transactionInfo.getRegisterId(), operation, Constants.REGISTER);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
            }
            if (transactionInfo.getAnnulTransactions() != null) {
                transactionInfo.getAnnulTransactions()
                        .forEach(t -> {
                            long operation = Constants.ANNUL;
                            try {
                                getInsertTxns(t, transactionInfo.getTimeStamp(), transactionInfo.getRegisterId(), operation, Constants.REGISTER);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Connection closed");
        }
    }

}
