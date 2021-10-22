public class Message8 extends Message {
    private int dac;
    private int fid;
    private float lat;
    private float lon;
    private int day;
    private int minute;
    private int hour;
    private int wspeed;
    private int wgust;
    private int wdir;
    private int wgustdir;
    private int temperature;
    private int humidity;
    private int dewpoint;
    private int pressure;
    private int pressuretend;
    private float visibility;
    private int leveltrend;
    private float waterlevel;
    private float cspeed, cspeed2, cspeed3;
    private float cdepth2, cdepth3;
    private int cdir, cdir2, cdir3;
    private float waveheight;
    private int waveperiod;
    private int wavedir;
    private float swellheight;
    private int swellperiod;
    private int swelldir;
    private int seastate;
    private float watertemp;
    private int preciptype;
    private float salinity;
    private int ice;

    private String reason;
    private String closefrom;
    private String closeto;
    private int radius;
    private int extunit;
    private int fday;
    private int fmonth;
    private int fhour;
    private int fminute;
    private int tday;
    private int tmonth;
    private int thour;
    private int tminute;

    private int airdraught;

    private int idtype;
    private int id;
    private int course;
    private int second;
    private int speed;

    private String station;
    private int status;
    private int signal;
    private int nextsignal;

    private int wmo;
    private String location;
    private int weather;
    private int vislimit;

    private int linkage;
    private String lastport;
    private String nextport;
    private String secondport;
    private int ais_state;
    private int ata_state;
    private int bnwas_state;
    private int ecdisb_state;
    private int chart_state;
    private int sounder_state;
    private int epaid_state;
    private int steer_state;
    private int gnss_state;
    private int gyro_state;
    private int lrit_state;
    private int magcomp_state;
    private int navtex_state;
    private int arpa_state;
    private int sband_state;
    private int xband_state;
    private int hfradio_state;
    private int inmarsat_state;
    private int mfradio_state;
    private int vhfradio_state;
    private int grndlog_state;
    private int waterlog_state;
    private int thd_state;
    private int tcs_state;
    private int vdr_state;
    private int iceclass;
    private int horsepower;
    private int vhfchan;
    private String lshiptype;
    private int tonnage;
    private int lading;
    private int heavyoil;
    private int lightoil;
    private int dieseloil;
    private int totaloil;
    private int persons;


    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        payload.getNextNbits(2); //sin usar
        dac =  payload.getNextNbits(10).toInteger();
        fid =  payload.getNextNbits(6).toInteger();
        if(dac == 1)
        {
            if(fid == 11)
            {
                lat = payload.getNextNbits(24).toSignedInt() * 0.0001f / 60;;
                lon = payload.getNextNbits(25).toSignedInt() * 0.0001f / 60;;
                day = payload.getNextNbits(5).toInteger();
                hour = payload.getNextNbits(5).toInteger();
                minute = payload.getNextNbits(6).toInteger();
                wspeed = payload.getNextNbits(7).toInteger();
                wgust = payload.getNextNbits(7).toInteger();
                wdir = payload.getNextNbits(9).toInteger();
                wgustdir = payload.getNextNbits(9).toInteger();
                temperature = payload.getNextNbits(11).toInteger();
                humidity = payload.getNextNbits(7).toInteger();
                dewpoint = payload.getNextNbits(10).toInteger();
                pressure = payload.getNextNbits(9).toInteger();
                pressuretend = payload.getNextNbits(2).toInteger();
                visibility = payload.getNextNbits(8).toInteger() * 0.1f;
                waterlevel = payload.getNextNbits(9).toSignedInt() * 0.1f;
                leveltrend = payload.getNextNbits(2).toInteger();
                cspeed = payload.getNextNbits(8).toInteger() * 0.1f;
                cdir = payload.getNextNbits(9).toInteger();
                cspeed2 = payload.getNextNbits(8).toInteger() * 0.1f;
                cdir2 = payload.getNextNbits(9).toInteger();
                cdepth2 = payload.getNextNbits(5).toInteger() * 0.1f;
                cspeed3 = payload.getNextNbits(8).toInteger() * 0.1f;
                cdir3 = payload.getNextNbits(9).toInteger();
                cdepth3 = payload.getNextNbits(5).toInteger() * 0.1f;
                waveheight = payload.getNextNbits(8).toInteger() * 0.1f;
                waveperiod = payload.getNextNbits(6).toInteger();
                wavedir = payload.getNextNbits(9).toInteger();
                swellheight = payload.getNextNbits(8).toInteger() * 0.1f;
                swellperiod = payload.getNextNbits(6).toInteger();
                swelldir = payload.getNextNbits(9).toInteger();
                seastate = payload.getNextNbits(4).toInteger();
                watertemp = payload.getNextNbits(10).toSignedInt() * 0.1f;
                preciptype = payload.getNextNbits(3).toInteger();
                salinity = payload.getNextNbits(9).toInteger() * 0.1f;
                ice = payload.getNextNbits(2).toInteger();
            }else if(fid == 13)
            {
                reason = payload.getNextNbits(120).toSixBitAscii();
                closefrom = payload.getNextNbits(120).toSixBitAscii();
                closeto = payload.getNextNbits(120).toSixBitAscii();
                radius = payload.getNextNbits(10).toInteger();
                extunit = payload.getNextNbits(2).toInteger();
                fday = payload.getNextNbits(5).toInteger();
                fmonth = payload.getNextNbits(4).toInteger();
                fhour = payload.getNextNbits(5).toInteger();
                fminute = payload.getNextNbits(6).toInteger();
                tday = payload.getNextNbits(5).toInteger();
                tmonth = payload.getNextNbits(4).toInteger();
                thour = payload.getNextNbits(5).toInteger();
                tminute = payload.getNextNbits(6).toInteger();
            }else if(fid == 15)
            {
                airdraught = payload.getNextNbits(11).toInteger();
            }
        }

    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("Código de área de asignada: %d\n", dac);
        System.out.printf("FID: %d\n", fid);
    }
    public int getDac() {
        return dac;
    }
    public void setDac(int dac) {
        this.dac = dac;
    }
    public int getFid() {
        return fid;
    }
    public void setFid(int fid) {
        this.fid = fid;
    }


}
