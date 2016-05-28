import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Khartonov Oleg on 21.05.2016.
 */
@XmlRootElement(name = "mpay-response")
public class TransactionInfo {
    private long registerId;
    private Timestamp timeStamp;
    private int registerType = Constants.REGISTER;
    private List<Transactions> transactions;
    private List<Transactions> annulTransactions;
    private String requestBlob;

    @XmlElement(name = "registerid")
    public long getRegisterId() {
        return registerId;
    }

    @XmlElement(name = "time_stamp")
    @XmlJavaTypeAdapter(DateConvert.class)
    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public int getRegisterType() {
        return registerType;
    }

    @XmlElementWrapper(name = "mps_pay")
    @XmlElement(name = "mp", type = Transactions.class)
    public List<Transactions> getTransactions() {
        return transactions;
    }

    @XmlElementWrapper(name = "mps_annul")
    @XmlElement(name = "mp", type = Transactions.class)
    public List<Transactions> getAnnulTransactions() {
        return annulTransactions;
    }

    public String getRequestBlob() {
        return requestBlob;
    }

    public void setRequestBlob(String requestBlob) {
        this.requestBlob = requestBlob;
    }

    public void setRegisterId(long registerId) {
        this.registerId = registerId;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setRegisterType(int registerType) {
        this.registerType = registerType;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    public void setAnnulTransactions(List<Transactions> annulTransactions) {
        this.annulTransactions = annulTransactions;
    }

    @XmlRootElement(name = "mp")
    public static class Transactions {
        private long id;
        private int operation;
        private long payId;
        private long pctransId;
        private Timestamp date;
        private long status;
        private long amount;
        private long fee;
        private long annulAmount;
        private long phone;
        private long goodsId;
        private long categoryId;
        private long compensationOperator;
        private long tradingConcession;
        private long branch;
        private String sourcePayment;

        @XmlElement(name = "pay_id")
        public long getPayId() {
            return payId;
        }

        @XmlElement(name = "pctransid")
        public long getPctransId() {
            return pctransId;
        }

        @XmlElement(name = "date")
        @XmlJavaTypeAdapter(DateConvert.class)
        public Timestamp getDate() {
            return date;
        }

        @XmlElement(name = "status")
        public long getStatus() {
            return status;
        }

        @XmlElement(name = "amount")
        public long getAmount() {
            return amount;
        }

        @XmlElement(name = "fee")
        public long getFee() {
            return fee;
        }

        @XmlElement(name = "annul_amount")
        public long getAnnulAmount() {
            return annulAmount;
        }

        @XmlElement(name = "phone")
        public long getPhone() {
            return phone;
        }

        @XmlElement(name = "goods_id")
        public long getGoodsId() {
            return goodsId;
        }

        @XmlElement(name = "category_id")
        public long getCategoryId() {
            return categoryId;
        }

        @XmlElement(name = "compensation_operator")
        public long getCompensationOperator() {
            return compensationOperator;
        }

        @XmlElement(name = "trading_concession")
        public long getTradingConcession() {
            return tradingConcession;
        }

        @XmlElement(name = "branch")
        public long getBranch() {
            return branch;
        }

        @XmlElement(name = "source_payment")
        public String getSourcePayment() {
            return sourcePayment;
        }

        public void setId(long id) {
            this.id = id;
        }

        public void setOperation(int operation) {
            this.operation = operation;
        }

        public void setPayId(long payId) {
            this.payId = payId;
        }

        public void setPctransId(long pctransId) {
            this.pctransId = pctransId;
        }

        public void setDate(Timestamp date) {
            this.date = date;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public void setAnnulAmount(int annulAmount) {
            this.annulAmount = annulAmount;
        }

        public void setPhone(long phone) {
            this.phone = phone;
        }

        public void setGoodsId(long goodsId) {
            this.goodsId = goodsId;
        }

        public void setCategoryId(long categoryId) {
            this.categoryId = categoryId;
        }

        public void setCompensationOperator(long compensationOperator) {
            this.compensationOperator = compensationOperator;
        }

        public void setTradingConcession(int tradingConcession) {
            this.tradingConcession = tradingConcession;
        }

        public void setBranch(int branch) {
            this.branch = branch;
        }

        public void setSourcePayment(String sourcePayment) {
            this.sourcePayment = sourcePayment;
        }
    }
}
