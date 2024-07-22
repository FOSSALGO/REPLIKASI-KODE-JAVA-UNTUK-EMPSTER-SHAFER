import java.util.ArrayList;

public class PenyakitMata {
    public static void main(String[] args) {
        String[] gejala =
        {
            "Penglihatan mata kabur atau tidak fokus",
            "Adanya garis gelombang dalam penglihatan",
            "Tidak bisa mengenal warna dengan baik",
            "Membutuhkan cahaya yang sangat terang untuk membaca",
            "Sulit untuk mengenali wajah",
            "Tidak bisa melihat warna cerah",
            "Mengalami halusinasi dalam meihat warna",
            "Sulit melihat pada malam hari",
            "Mata menjadi sensitif terhadap cahaya /silau",
            "Ada lingkaran putih dalam sumber cahaya seperti lampu",
            "Penglihatan mata menjadi ganda",
            "Nyeri pada bagian belakang mata",
            "Gangguan penglihatan",
            "Melihat bayangan lampu berkedip",
            "Penglihatan menjadi tidak jelas pada bagian tepi",
            "Sakit mata",
            "Mual dan muntah pada saat sakit mata",
            "Tidak bisa melihat saat redup atau tidak ada cahaya",
            "Mata merah",
            "Mata menjadi lebih menonjol",
            "Ada tekanan kuat pada bagian dalam mata",
            "Mata seperti menghasilkan pasir",
            "Kelopak mata seperti tertarik",
            "Mata kehilangan kemampuan untuk melihat",
            "Nyeri pada mata",
            "Nyeri saat mengerakan kelopak mata",
            "Rasa takut abnormal pada cahaya (fotofobia)",
            "Mata berair",
            "Kecenderungan untuk memegang bacaan lebih jauh agar bisa melihat huruf lebih jelas",
            "Menyipitkan mata",
            "Penglihatan kabur ketika membaca dengan jarak normal",
            "Sakit kepala atau mata menegang pada saat membaca",
            "Kesulitan membaca cetakan huruf berukuran kecil",
            "Mata seperti melihat bintik-bintik kecil pada pandangan",
            "Mata seperti tertutup oleh rambut atau beberapa benang kecil meskipun sebenarnya tidak",
            "Mata memberikan respon berkedip dalam waktu cepat saat melihat cahaya",
            "Mengalami penglihatan seperti ada bintik-bintik hitam beterbangan",
        };

        String[] penyakit =
        {
            "Degenerasi Makula",
            "Katarak",
            "Neuristik Optik",
            "Glukoma Sudut Terbuka",
            "Glukoma Sudut Tertutup",
            "Graves",
            "Keratitis",
            "Presbiopi",
            "Ablasi Retina",
            "Iridosiklitis Akut",
        };
        
        int[][] relasi = {
            {0,1,2,3,4,5,6},  
            {0,7,8,9,10},  
            {11,12,2,13},  
            {15,16,17,9,18},  
            {15,16,17,9,18},  
            {19,20,21,22,23,18,8,10},  
            {18,23,24,8,25,26,27},  
            {28,29,30,31,32},  
            {33,34,35},  
            {18,36,15,26,23,27},  
        };
        
        //CETAK RELASI
        for (int i = 0; i < penyakit.length; i++)
        {
            System.out.print(penyakit[i]+": ");
            for (int j = 0; j < relasi[i].length; j++)
            {
                int indexGejala = relasi[i][j];
                System.out.print(gejala[indexGejala]+", ");
                
            }
            System.out.println();
        }
        
        //NILAI GEJALA
        double[][] nilaiGejala = new double[penyakit.length][];
        for (int i = 0; i < relasi.length; i++)
        {
            nilaiGejala[i] = new double[relasi[i].length];
            double value = 1.0/relasi[i].length;
            for (int j = 0; j < relasi[i].length; j++)
            {
                nilaiGejala[i][j] = value;
            }
            
        }
        
        // CETAK NILAI GEJALA
        for (int i = 0; i < relasi.length; i++)
        {
            System.out.println("=====================================================");
            System.out.println(penyakit[i]+": ");
            for (int j = 0; j < relasi[i].length; j++)
            {
                int indexGejala = relasi[i][j];
                System.out.println(gejala[indexGejala]+"("+nilaiGejala[i][j]+")"+", ");
                
            }
            System.out.println();
        }
        
        // FUNGSI DENSITAS
        ArrayList<Integer>[] simbolFungsiDensitas = new ArrayList[gejala.length];
        for (int i = 0; i < simbolFungsiDensitas.length; i++)
        {
            simbolFungsiDensitas[i] = new ArrayList<>();
        } 
        
        // MENDETEKSI GEJALA G ADA DI PENYAKIT APA SAJA
        for (int i = 0; i < penyakit.length; i++)
        {
            for (int j = 0; j < relasi[i].length; j++)
            {
                int indexGejala = relasi[i][j];
                simbolFungsiDensitas[indexGejala].add(i); // Menambahkan Simbol Penyakit pada gejala
                
            }
        }
        
        //Hitung Belief and Plausality
        double[]belief = new double[gejala.length];
        double[]plausibility = new double[gejala.length];
        for (int i = 0; i < belief.length; i++)
        {
            double b = 0;
            double n = simbolFungsiDensitas[i].size();
            for (int j = 0; j <  simbolFungsiDensitas[i].size(); j++)
            {
                int indexPenyakit = simbolFungsiDensitas[i].get(j);
                int indexGejala = 0;
                for (int k = 0; k < relasi[indexPenyakit].length; k++)
                {
                    if(relasi[indexPenyakit][k]==i){
                        indexGejala = k;
                        break;
                    }
                }b += nilaiGejala[indexPenyakit][indexGejala];
            }
            //antisipasi N bernilai = 0 yang akan menyebabkan DIV by 0 maka beri nilai default sebesar epsilon jika N=0
            double epsilon = 0.00000000001;
            if(n<=0){
                n = epsilon;
            }
            belief[i]=b/n;
            plausibility[i]=1-belief[i];
        }
        
        // CETAK SIMBOL FUNGSI DENSITAS
        for (int i = 0; i < simbolFungsiDensitas.length; i++)
        {
            System.out.print("G"+(1+i)+"{");
            for (int j = 0; j < simbolFungsiDensitas[i].size();j++)
            {
                if(j>0)System.out.print(", ");
                System.out.print("P"+(1+j));         
            }
            System.out.println("}\tbelief: "+belief[i]+"\tplausibility: "+plausibility[i]);
        }
        
        // INPUT GEJALA PENYAKIT YANG AKAN DIDETEKSI
        int[] gejalaTest= {33,34,35};      
        
        if (gejalaTest != null)
        {
            int indexGejala1 = gejalaTest[0];
            // m = probabilitas fungsi densitas
            ArrayList<Integer>[] m1Symbols = new ArrayList[2];
            double[] m1Values = new double[2];
            //symbols
            m1Symbols[0] = simbolFungsiDensitas[indexGejala1];
            m1Symbols[1] = new ArrayList<>();
            m1Symbols[1].add(-1);// -1 adalah simbol untuk theta; untuk memudahkan symbol θ diberikan untuk elemen terakhir
            //values
            m1Values[0] = plausibility[indexGejala1];
            m1Values[1] = belief[indexGejala1];

            if (gejalaTest.length > 1)
            {
                // hitung densitas baru
                for (int i = 1; i < gejalaTest.length; i++)
                {
                    int indexGejala2 = gejalaTest[i];
                    ArrayList<Integer>[] m2Symbols = new ArrayList[2];
                    double[] m2Values = new double[2];
                    // symbols
                    m2Symbols[0] = simbolFungsiDensitas[indexGejala2];
                    m2Symbols[1] = new ArrayList<>();
                    m2Symbols[1].add(-1);// -1 adalah simbol untuk theta; untuk memudahkan symbol θ diberikan untuk elemen terakhir
                    // values
                    m2Values[0] = plausibility[indexGejala1];
                    m2Values[1] = belief[indexGejala1];
                    
                    ArrayList<Integer>[][]matrixSymbols = new ArrayList[m1Values.length][2];
                    double[][]matrixValues = new double[m1Values.length][2];
                    double sumNULL = 0;
                    // hitung nilai matrix
                    for (int j = 0; j < matrixValues.length; j++)
                    {
                        for (int k = 0; k < matrixValues[j].length; k++)
                        {
                            matrixValues[j][k]=m1Values[j]*m2Values[k];
                            
                            matrixSymbols[j][k]=null;// nilai default matrixSymbols matrixSymbols[j][k] = {Ø} 
                            ArrayList<Integer> symbol1 = m1Symbols[j];
                            ArrayList<Integer> symbol2 = m2Symbols[k];
                            if(j<matrixValues.length-1){// jika j bukan index elemen terakhir
                                if(k<matrixValues[j].length-1){// jika k bukan index elemen terakhir
                                    // ini untuk yang ada subset
                                    boolean isSubset = true;
                                    for (int l = 0; l < symbol1.size(); l++)
                                    {
                                        boolean ada = false;
                                        for (int m = 0; m < symbol2.size(); m++)
                                        {
                                            if(symbol1.get(l)==symbol2.get(m)){
                                                ada = true;
                                                break;
                                            }
                                        }
                                        if(!ada){
                                            isSubset = false;
                                            break;
                                        }
                                    }
                                    // jika merupakan himpunan bagian maka symbol1 di-set ke matrixSymbols[j][k]
                                    matrixSymbols[j][k] = symbol1;
                                }else{
                                    matrixSymbols[j][k] = symbol1;
                                }
                            }else{
                                if(k<matrixValues[j].length-1){// jika k bukan index elemen terakhir
                                     matrixSymbols[j][k] = symbol2;
                                }else{
                                    matrixSymbols[j][k] = new ArrayList<>();
                                    matrixSymbols[j][k].add(-1);// -1 adalah simbol untuk theta; untuk memudahkan symbol θ diberikan untuk elemen terakhir                    
                                }
                            }
                            
                            // update sumNULL
                            if(matrixSymbols[j][k]==null){
                                sumNULL += matrixValues[j][k];
                            }
                            
                        }
                    }// pengisian matrix selesai
                    
                    // hitung densitas baru
                    ArrayList<Integer>[] newSymbols = new ArrayList[m1Symbols.length+1];
                    double[] newValues = new double[m1Values.length+1];
                    double denominator = 1 - sumNULL;// penyebut
                    for (int j = 0; j < m1Symbols.length-1; j++)
                    {
                        // set newSymbols
                        newSymbols[j]=new ArrayList<>();
                        for (int k = 0; k < m1Symbols[j].size(); k++)
                        {
                            newSymbols[j].add(m1Symbols[j].get(k));
                        }
                        double numerator = matrixValues[j][1];//pembilang diambil dari kolom θ
                        newValues[j]= numerator / denominator;
                    }
                    
                    // symbol untuk gejala baru
                    newSymbols[m1Symbols.length - 1] = new ArrayList<>();
                    for (int k = 0; k < m2Symbols[0].size(); k++)
                    {
                        newSymbols[m1Symbols.length - 1].add(m2Symbols[0].get(k));
                    }
                    double numerator = matrixValues[matrixValues.length-1][0];//pembilang diambil dari kolom θ
                    newValues[m1Symbols.length - 1]= numerator / denominator;
                    
                    // symbol untuk theta
                    newSymbols[newSymbols.length-1] = new ArrayList<>();
                    newSymbols[newSymbols.length-1].add(-1);// -1 adalah simbol untuk theta; untuk memudahkan symbol θ diberikan untuk elemen terakhir                    
                    numerator = matrixValues[matrixValues.length-1][matrixValues[0].length-1];
                    newValues[newSymbols.length-1] = numerator / denominator;
                    
                    // SET NEW SYMBOLS dan NEW VALUES
                    m1Symbols = newSymbols;
                    m1Values = newValues;
                }
                
                // UNTUK MELIHAT KESIMPULAN AKHIR
                // identifikasi penyakit apa saja yang terkait dengan gejala
                // hitung nilai densitas masing-masing penyakit
                ArrayList<Integer>penyakitTerdeteksi = new ArrayList<>();
                for (int i = 0; i < m1Symbols.length; i++)
                {
                    for (int j = 0; j < m1Symbols[i].size(); j++)
                    {
                        int symbol = m1Symbols[i].get(j);
                        if (symbol != -1 && !penyakitTerdeteksi.contains(symbol))
                        {
                            penyakitTerdeteksi.add(symbol);
                        }
                    }
                }
                //nilai densitas masing-masing penyakit
                int iMAX = -1;
                double MAX_DENSITY = Double.MIN_VALUE;
                double[] densitasPenyakit = new double[penyakitTerdeteksi.size()]; // m adalah densitasPenyakit
                for (int i = 0; i < penyakitTerdeteksi.size(); i++)
                {
                    double value = 0;
                    int p = penyakitTerdeteksi.get(i);
                    for (int j = 0; j < m1Symbols.length; j++)
                    {
                        if(m1Symbols[j].contains(p)){
                            value += m1Values[j];
                        }
                    }
                    densitasPenyakit[i] = value;
                    if(densitasPenyakit[i]>MAX_DENSITY){
                        MAX_DENSITY = densitasPenyakit[i];
                        iMAX = i;
                    }
                }
                
                System.out.println("---------------------------------------------");
                // print densitas penyakit
                for (int i = 0; i < penyakitTerdeteksi.size(); i++){
                    double value = densitasPenyakit[i];
                    System.out.println("m{P"+(1+penyakitTerdeteksi.get(i))+"} = "+value);
                }
                
                //OUTPUT
                System.out.println("---------------------------------------------");
                int indexPenyakitDiagnosa = penyakitTerdeteksi.get(iMAX);
                String penyakitDiagnosa = penyakit[indexPenyakitDiagnosa];
                System.out.println("Hasil Diagnosa: "+penyakitDiagnosa);
                

            }
        }// end of if(gejalaTest!=null){
        
        
    }
}
