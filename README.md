
# Bank Rule Engine Compare

Bu depo, Ahmet Yesevi Üniversitesi Yazılım Mühendisliği Anabilim Dalı yüksek lisans tezi kapsamında hazırlanan:

**“Drools ve Easy Rules Kural Motorlarının Performans ve Yönetilebilirlik Açısından Karşılaştırılması”**  
_Mehmet Karacan – 2025_

başlıklı çalışmaya ait kaynak kodları, veri setleri, betikler ve sonuç dosyalarını içermektedir.

---

## 📌 Proje Özeti

Bu çalışmada, açık kaynak kodlu Java tabanlı iki farklı kural motoru olan **Drools** ve **Easy Rules**, aynı veri seti üzerinde, farklı veri hacmi ve kural karmaşıklığı düzeylerinde karşılaştırılmıştır. Karşılaştırma; **çalışma süresi, CPU kullanımı ve RAM kullanımı** ölçütleri temel alınarak yapılmıştır.

Bankacılık sektörüne ait gerçekçi bir veri seti kullanılmış ve kural motorlarının çıktıları PostgreSQL veritabanına kaydedilerek analiz edilmiştir.

Veri üretimi, orijinal `bank-marketing.csv` veri seti referans alınarak sentetik ama gerçekçi örüntülerle oluşturulmuştur.

---

## 📁 Klasör Yapısı

```
BankRuleEngineCompare/
├── pom.xml                                       # Maven yapılandırma dosyası
├── README.md                                     # Bu açıklama dosyası
├── .gitignore                                    # Git yoksayılacak dosyalar
├── data/
│   └── bank-marketing.csv                        # Orijinal veri seti (Bank Marketing)
├── results/
│   └── rule_engine_log.csv                       # Veritabanı dışına aktarılan özet log (analiz amaçlı)
├── scripts/                                      # Tüm PostgreSQL betikleri burada
│   ├── analyze_performance_summary.sql           # Performans karşılaştırma SQL sorgusu
│   ├── create_bank_marketing_table.sql           # Orijinal veri tablosu (İngilizce alan adları)
│   ├── create_banka_pazarlama_verisi_table.sql   # Analiz tablosu (Türkçe alanlar)
│   ├── create_log_table.sql                      # Log kayıt tablosu
│   ├── insert_bank_marketing_transformation.sql  # İngilizce->Türkçe veri aktarım betiği
│   └── generate_data.sql                         # Rastgele veri üretim betiği
├── src/
│   └── main/
│       └── resources/
│           ├── application.properties            # Veritabanı ve motor ayarları
│           ├── META-INF/
│           │   └── kmodule.xml                   # Drools yapılandırma dosyası
│           └── rules/
│               ├── drools/                       # Drools kuralları
│               │   ├── rules_25.drl
│               │   ├── rules_50.drl
│               │   ├── rules_75.drl
│               │   └── rules_100.drl
│               └── easyrules/                    # Easy Rules YAML kuralları
│                   ├── rules_1.yml ... rules_100.yml
```

---

## 🧪 Test Senaryoları

- 4 farklı **kural zorluk seviyesi**:
    - **Drools** için: `rules_25.drl`, `rules_50.drl`, `rules_75.drl`, `rules_100.drl` dosyaları sırasıyla artan kural karmaşıklıklarını temsil eder
    - **Easy Rules** için: `rules_1_25.yml`, `rules_26_50.yml`, `rules_51_75.yml`, `rules_76_100.yml` dosyaları aynı şekilde dört farklı zorluk düzeyine karşılık gelir
- 3 farklı veri hacmi: 50.000, 100.000 ve 500.000 kayıt
- Her senaryo 100 kez çalıştırılarak ortalama metrikler alınmıştır

---

## 🧬 Veri Üretimi

`generate_data.sql` betiği, orijinal `bank-marketing.csv` dosyasındaki kategorik ve sayısal değerlerin örüntülerine bağlı kalarak rastgele ama gerçekçi görünümlü veri üretir. Bu sentetik veriler, test ortamlarında kullanılan `banka_pazarlama_verisi` tablosuna eklenir.

---

## 📊 Performans Analizi

- Veriler PostgreSQL veritabanında `kural_motoru_karsilastirma_logu` tablosunda toplanmıştır
- Özet analizler için: `scripts/analyze_performance_summary.sql`

---

## ⚙️ Kullanım

1. `scripts/create_bank_marketing_table.sql` dosyasını çalıştırarak `bank_marketing` tablosunu oluşturun
2. `data/bank-marketing.csv` dosyasını pgAdmin, DBeaver gibi bir araçla bu tabloya CSV olarak aktarın
3. `scripts/create_banka_pazarlama_verisi_table.sql` dosyasını çalıştırarak Türkçe alanlı analiz tablosunu oluşturun
4. `scripts/insert_bank_marketing_transformation.sql` ile İngilizce verileri Türkçe tabloya aktarın
5. `scripts/generate_data.sql` ile rastgele ama gerçekçi veri üretin
6. `scripts/create_log_table.sql` ile log tablosunu oluşturun
7. Java uygulamasını çalıştırın (`Drools` ve `Easy Rules`)
8. Sonuçları `results/rule_engine_log.csv` dosyasından ve veritabanındaki `kural_motoru_karsilastirma_logu` tablosundan analiz edin

---

## 🧾 Lisans

Bu proje [MIT Lisansı](LICENSE) ile lisanslanmıştır.
