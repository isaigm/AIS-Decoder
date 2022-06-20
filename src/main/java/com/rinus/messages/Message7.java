/*
    +---------+-----+---------------------+----------+---+-------------------------------+
    | Field   | Len | Description         | Member   | T | Units                         |
    +---------+-----+---------------------+----------+---+-------------------------------+
    | 0-5     | 6   | Message Type        | type     | u | Constant: 7                   |
    +---------+-----+---------------------+----------+---+-------------------------------+
    | 6-7     | 2   | Repeat Indicator    | repeat   | u | As in Common Navigation Block |
    +---------+-----+---------------------+----------+---+-------------------------------+
    | 8-37    | 30  | Source MMSI         | mmsi     | u | 9 decimal digits              |
    +---------+-----+---------------------+----------+---+-------------------------------+
    | 38-39   | 2   | Spare               |          | x | Not used                      |
    +---------+-----+---------------------+----------+---+-------------------------------+
    | 40-69   | 30  | MMSI number 1       | mmsi1    | u | 9 decimal digits              |
    +---------+-----+---------------------+----------+---+-------------------------------+
    | 70-71   | 2   | Sequence for MMSI 1 | mmsiseq1 | u | Not used                      |
    +---------+-----+---------------------+----------+---+-------------------------------+
    | 72-101  | 30  | MMSI number 2       | mmsi2    | u | 9 decimal digits              |
    +---------+-----+---------------------+----------+---+-------------------------------+
    | 102-103 | 2   | Sequence for MMSI 2 | mmsiseq2 | u | Not used                      |
    +---------+-----+---------------------+----------+---+-------------------------------+
    | 104-133 | 30  | MMSI number 3       | mmsi3    | u | 9 decimal digits              |
    +---------+-----+---------------------+----------+---+-------------------------------+
    | 134-135 | 2   | Sequence for MMSI 3 | mmsiseq3 | u | Not used                      |
    +---------+-----+---------------------+----------+---+-------------------------------+
    | 136-165 | 30  | MMSI number 4       | mmsi4    | u | 9 decimal digits              |
    +---------+-----+---------------------+----------+---+-------------------------------+
    | 166-167 | 2   | Sequence for MMSI 4 | mmsiseq4 | u | Not used                      |
    +---------+-----+---------------------+----------+---+-------------------------------+
 */
package com.rinus.messages;
import com.rinus.decoder.Payload;
import com.rinus.messages.Message;

public class Message7 extends Message {
    private int mmsi1;
    private int mmsiseq1;
    private int mmsi2;
    private int mmsiseq2;
    private int mmsi3;
    private int mmsiseq3;
    private int mmsi4;
    private int mmsiseq4;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        payload.getNextNbits(2); // sin usar
        if(payload.getRemainingSize() >= 32)
        {
            mmsi1 = payload.getNextNbits(30).toInteger();
            mmsiseq1 = payload.getNextNbits(2).toInteger();
        }
        if(payload.getRemainingSize() >= 32)
        {
            mmsi2 =  payload.getNextNbits(30).toInteger();
            mmsiseq2 = payload.getNextNbits(2).toInteger();
        }
        if(payload.getRemainingSize() >= 32)
        {
            mmsi3 = payload.getNextNbits(30).toInteger();
            mmsiseq3 = payload.getNextNbits(2).toInteger();
        }
        if(payload.getRemainingSize() >= 32)
        {
            mmsi4 = payload.getNextNbits(30).toInteger();
            mmsiseq4 = payload.getNextNbits(2).toInteger();
        }
    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("MMSI1: %d\n", mmsi1);
        System.out.printf("MMSIseq1: %d\n", mmsiseq1);
        System.out.printf("MMSI2: %d\n", mmsi2);
        System.out.printf("MMSIseq2: %d\n", mmsiseq2);
        System.out.printf("MMSI3: %d\n", mmsi3);
        System.out.printf("MMSIseq3: %d\n", mmsiseq3);
        System.out.printf("MMSI4: %d\n", mmsi4);
        System.out.printf("MMSIseq4: %d\n", mmsiseq4);
    }
    public int getMmsi1() {
        return mmsi1;
    }
    public void setMmsi1(int mmsi1) {
        this.mmsi1 = mmsi1;
    }
    public int getMmsiseq1() {
        return mmsiseq1;
    }
    public void setMmsiseq1(int mmsiseq1) {
        this.mmsiseq1 = mmsiseq1;
    }
    public int getMmsi2() {
        return mmsi2;
    }
    public void setMmsi2(int mmsi2) {
        this.mmsi2 = mmsi2;
    }
    public int getMmsiseq2() {
        return mmsiseq2;
    }
    public void setMmsiseq2(int mmsiseq2) {
        this.mmsiseq2 = mmsiseq2;
    }
    public int getMmsi3() {
        return mmsi3;
    }
    public void setMmsi3(int mmsi3) {
        this.mmsi3 = mmsi3;
    }
    public int getMmsiseq3() {
        return mmsiseq3;
    }
    public void setMmsiseq3(int mmsiseq3) {
        this.mmsiseq3 = mmsiseq3;
    }
    public int getMmsi4() {
        return mmsi4;
    }
    public void setMmsi4(int mmsi4) {
        this.mmsi4 = mmsi4;
    }
    public int getMmsiseq4() {
        return mmsiseq4;
    }
    public void setMmsiseq4(int mmsiseq4) {
        this.mmsiseq4 = mmsiseq4;
    }
}
