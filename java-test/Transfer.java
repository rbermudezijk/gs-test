
class Transfer {
    private Float transferAmount;
    private Date transferDate;
    private String originAccount;
    private String destinationAccount;
    private Boolean transferPosted;

    public Transfer(){}

    public Transfer(
        Float transferAmount,
        Date transferDate,
        String originAccount,
        String destinationAccount,
        Boolean transferPosted
    ) {
        this.transferAmount = transferAmount;
        this.transferDate = transferDate;
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
        this.transferPosted = transferPosted;
    }

    public Boolean isTransferApplied() {
        return transferPosted;
    }

    public void transferApplied() {
        transferPosted = true;
    }
}