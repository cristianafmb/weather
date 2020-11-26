
package com.example.demo;

import com.example.demo.Model.City;
import com.example.demo.Model.Weather;
import com.example.demo.Repository.CityRepository;
import com.example.demo.Repository.WeatherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Transactional
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CityRepository citiesRepo;
    private WeatherRepository weatherRepo;

    public Bootstrap(CityRepository citiesRepo, WeatherRepository weatherRepository) {
       this.citiesRepo = citiesRepo;
       this.weatherRepo = weatherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        City Aveiro = new City("aveiro", "North");
        LocalDateTime now = LocalDateTime.now();
        Weather a1 = new Weather(now,14, 21,100, 90, 18, Aveiro);
        Weather a2 = new Weather(now.plusDays(1),9, 16,1, 68, 11, Aveiro);
        Weather a3 = new Weather(now.plusDays(2),8, 16,10, 79, 11, Aveiro);
        Weather a4 = new Weather(now.plusDays(3),11, 17,80, 74, 23, Aveiro);
        Weather a5 = new Weather(now.plusDays(4),11, 16,40, 74, 18, Aveiro);
        this.weatherRepo.save(a1);
        this.weatherRepo.save(a2);
        this.weatherRepo.save(a3);
        this.weatherRepo.save(a4);
        this.weatherRepo.save(a5);
        Aveiro.addWeather(a1);
        Aveiro.addWeather(a2);
        Aveiro.addWeather(a3);
        Aveiro.addWeather(a4);
        Aveiro.addWeather(a5);
        this.citiesRepo.save(Aveiro);

        City VianaDoCastelo = new City("vianadocastelo", "North");
        Weather vc1 = new Weather(now,9, 16,50, 73, 6, VianaDoCastelo);
        Weather vc2 = new Weather(now.plusDays(1),9, 16,10, 81, 6, VianaDoCastelo);
        Weather vc3 = new Weather(now.plusDays(2),11, 17,20, 73, 14, VianaDoCastelo);
        Weather vc4 = new Weather(now.plusDays(3),10, 16,40, 74, 13, VianaDoCastelo);
        Weather vc5 = new Weather(now.plusDays(4),10, 19,10, 70, 13, VianaDoCastelo);
        this.weatherRepo.save(vc1);
        this.weatherRepo.save(vc2);
        this.weatherRepo.save(vc3);
        this.weatherRepo.save(vc4);
        this.weatherRepo.save(vc5);
        VianaDoCastelo.addWeather(vc1);
        VianaDoCastelo.addWeather(vc2);
        VianaDoCastelo.addWeather(vc3);
        VianaDoCastelo.addWeather(vc4);
        VianaDoCastelo.addWeather(vc5);
        this.citiesRepo.save(VianaDoCastelo);


        City Braga = new City("braga", "North");
        Weather b1 = new Weather(now,9, 15,8, 76, 6, Braga);
        Weather b2 = new Weather(now.plusDays(1),7, 17,10, 79, 8, Braga);
        Weather b3 = new Weather(now.plusDays(2),10, 17,50, 75, 19, Braga);
        Weather b4 = new Weather(now.plusDays(3),9, 16,30, 74, 14, Braga);
        Weather b5 = new Weather(now.plusDays(4),9, 18,10, 72, 11, Braga);
        this.weatherRepo.save(b1);
        this.weatherRepo.save(b2);
        this.weatherRepo.save(b3);
        this.weatherRepo.save(b4);
        this.weatherRepo.save(b5);
        Braga.addWeather(b1);
        Braga.addWeather(b2);
        Braga.addWeather(b3);
        Braga.addWeather(b4);
        Braga.addWeather(b5);
        this.citiesRepo.save(Braga);

        City VilaReal = new City("vilareal", "North");
        Weather vr1 = new Weather(now,8, 11,38, 86, 6, VilaReal);
        Weather vr2 = new Weather(now.plusDays(1),6, 14,10, 81, 6, VilaReal);
        Weather vr3 = new Weather(now.plusDays(2),8, 13,20, 84, 10, VilaReal);
        Weather vr4 = new Weather(now.plusDays(3),7, 12,40, 82, 10, VilaReal);
        Weather vr5 = new Weather(now.plusDays(4),6, 14,10, 80, 8, VilaReal);
        this.weatherRepo.save(vr1);
        this.weatherRepo.save(vr2);
        this.weatherRepo.save(vr3);
        this.weatherRepo.save(vr4);
        this.weatherRepo.save(vr5);
        VilaReal.addWeather(vr1);
        VilaReal.addWeather(vr2);
        VilaReal.addWeather(vr3);
        VilaReal.addWeather(vr4);
        VilaReal.addWeather(vr5);
        this.citiesRepo.save(VilaReal);

        City Porto = new City("porto", "North");
        Weather p1 = new Weather(now,9, 16,73, 77, 8, Porto);
        Weather p2 = new Weather(now.plusDays(1),9, 16,10, 81, 11, Porto);
        Weather p3 = new Weather(now.plusDays(2),11, 17,60, 73, 21, Porto);
        Weather p4 = new Weather(now.plusDays(3),11, 16,40, 74, 16, Porto);
        Weather p5 = new Weather(now.plusDays(4),12, 18,10, 70, 18, Porto);
        this.weatherRepo.save(p1);
        this.weatherRepo.save(p2);
        this.weatherRepo.save(p3);
        this.weatherRepo.save(p4);
        this.weatherRepo.save(p5);
        Porto.addWeather(p1);
        Porto.addWeather(p2);
        Porto.addWeather(p3);
        Porto.addWeather(p4);
        Porto.addWeather(p5);
        this.citiesRepo.save(Porto);



        City Braganca = new City("bragança", "North");
        Weather bg1 = new Weather(now,6, 11,54, 77, 13, Braganca);
        Weather bg2 = new Weather(now.plusDays(1),3, 13,10, 80, 11, Braganca);
        Weather bg3 = new Weather(now.plusDays(2),5, 11,10, 84, 6, Braganca);
        Weather bg4 = new Weather(now.plusDays(3),4, 11,60, 83, 8, Braganca);
        Weather bg5 = new Weather(now.plusDays(4),4, 13,10, 77, 10, Braganca);
        this.weatherRepo.save(bg1);
        this.weatherRepo.save(bg2);
        this.weatherRepo.save(bg3);
        this.weatherRepo.save(bg4);
        this.weatherRepo.save(bg5);
        Braganca.addWeather(bg1);
        Braganca.addWeather(bg2);
        Braganca.addWeather(bg3);
        Braganca.addWeather(bg4);
        Braganca.addWeather(bg5);
        this.citiesRepo.save(Braganca);

        City Viseu = new City("viseu", "North");
        Weather v1 = new Weather(now,7, 12,80, 84, 11, Viseu);
        Weather v2 = new Weather(now.plusDays(1),6, 14,10, 78, 14, Viseu);
        Weather v3 = new Weather(now.plusDays(2),8, 14,20, 76, 16, Viseu);
        Weather v4 = new Weather(now.plusDays(3),9, 12,40, 79, 14, Viseu);
        Weather v5 = new Weather(now.plusDays(4),9, 15,10, 73, 18, Viseu);
        this.weatherRepo.save(v1);
        this.weatherRepo.save(v2);
        this.weatherRepo.save(v3);
        this.weatherRepo.save(v4);
        this.weatherRepo.save(v5);
        Viseu.addWeather(v1);
        Viseu.addWeather(v2);
        Viseu.addWeather(v3);
        Viseu.addWeather(v4);
        Viseu.addWeather(v5);
        this.citiesRepo.save(Viseu);

        City Guarda = new City("guarda", "North");
        Weather g1 = new Weather(now,4, 8,22, 90, 6, Guarda);
        Weather g2 = new Weather(now.plusDays(1),4, 11,20, 87, 13, Guarda);
        Weather g3 = new Weather(now.plusDays(2),6, 10,20, 84, 19, Guarda);
        Weather g4 = new Weather(now.plusDays(3),6, 9,60, 87, 16, Guarda);
        Weather g5 = new Weather(now.plusDays(4),4, 12,20, 79, 11, Guarda);
        this.weatherRepo.save(g1);
        this.weatherRepo.save(g2);
        this.weatherRepo.save(g3);
        this.weatherRepo.save(g4);
        this.weatherRepo.save(g5);
        Guarda.addWeather(g1);
        Guarda.addWeather(g2);
        Guarda.addWeather(g3);
        Guarda.addWeather(g4);
        Guarda.addWeather(g5);
        this.citiesRepo.save(Guarda);

        City Coimbra = new City("coimbra", "Center");
        Weather c1 = new Weather(now,8, 17,6, 69, 10, Coimbra);
        Weather c2 = new Weather(now.plusDays(1),8, 17,20, 78, 8, Coimbra);
        Weather c3 = new Weather(now.plusDays(2),11, 17,80, 72, 18, Coimbra);
        Weather c4 = new Weather(now.plusDays(3),11, 15,30, 74, 16, Coimbra);
        Weather c5 = new Weather(now.plusDays(4),11, 18,10, 69, 16, Coimbra);
        this.weatherRepo.save(c1);
        this.weatherRepo.save(c2);
        this.weatherRepo.save(c3);
        this.weatherRepo.save(c4);
        this.weatherRepo.save(c5);
        Coimbra.addWeather(c1);
        Coimbra.addWeather(c2);
        Coimbra.addWeather(c3);
        Coimbra.addWeather(c4);
        Coimbra.addWeather(c5);
        this.citiesRepo.save(Coimbra);

        City CasteloBranco = new City("castelobranco", "Center");
        Weather cb1 = new Weather(now,7, 11,7, 87, 11, CasteloBranco);
        Weather cb2 = new Weather(now.plusDays(1),6, 14,20, 77, 11, CasteloBranco);
        Weather cb3 = new Weather(now.plusDays(2),8, 13,20, 79, 14, CasteloBranco);
        Weather cb4 = new Weather(now.plusDays(3),8, 13,60, 80, 14, CasteloBranco);
        Weather cb5 = new Weather(now.plusDays(4),7, 15,30, 74, 16, CasteloBranco);
        this.weatherRepo.save(cb1);
        this.weatherRepo.save(cb2);
        this.weatherRepo.save(cb3);
        this.weatherRepo.save(cb4);
        this.weatherRepo.save(cb5);
        CasteloBranco.addWeather(cb1);
        CasteloBranco.addWeather(cb2);
        CasteloBranco.addWeather(cb3);
        CasteloBranco.addWeather(cb4);
        CasteloBranco.addWeather(cb5);
        this.citiesRepo.save(CasteloBranco);

        City Leiria = new City("leiria", "Center");
        Weather l1 = new Weather(now,8, 17,24, 74, 13, Leiria);
        Weather l2 = new Weather(now.plusDays(1),8, 16,20, 80, 11, Leiria);
        Weather l3 = new Weather(now.plusDays(2),9, 16,90, 82, 24, Leiria);
        Weather l4 = new Weather(now.plusDays(3),11, 15,40, 80, 19, Leiria);
        Weather l5 = new Weather(now.plusDays(4),12, 17,10, 73, 23, Leiria);
        this.weatherRepo.save(l1);
        this.weatherRepo.save(l2);
        this.weatherRepo.save(l3);
        this.weatherRepo.save(l4);
        this.weatherRepo.save(l5);
        Leiria.addWeather(l1);
        Leiria.addWeather(l2);
        Leiria.addWeather(l3);
        Leiria.addWeather(l4);
        Leiria.addWeather(l5);
        this.citiesRepo.save(Leiria);

        City Santarem = new City("santarem", "Center");
        Weather s1 = new Weather(now,7, 16,2, 75, 18, Santarem);
        Weather s2 = new Weather(now.plusDays(1),6, 16,10, 79, 10, Santarem);
        Weather s3 = new Weather(now.plusDays(2),9, 16,90, 83, 21, Santarem);
        Weather s4 = new Weather(now.plusDays(3),11, 16,50, 82, 18, Santarem);
        Weather s5 = new Weather(now.plusDays(4),11, 18,20, 76, 21, Santarem);
        this.weatherRepo.save(s1);
        this.weatherRepo.save(s2);
        this.weatherRepo.save(s3);
        this.weatherRepo.save(s4);
        this.weatherRepo.save(s5);
        Santarem.addWeather(s1);
        Santarem.addWeather(s2);
        Santarem.addWeather(s3);
        Santarem.addWeather(s4);
        Santarem.addWeather(s5);
        this.citiesRepo.save(Santarem);

        City Portalegre = new City("portalegre", "Center");
        Weather pa1 = new Weather(now,8, 11,4, 89, 16, Portalegre);
        Weather pa2 = new Weather(now.plusDays(1),8, 20,18, 80, 11, Portalegre);
        Weather pa3 = new Weather(now.plusDays(2),9, 13,70, 78, 21, Portalegre);
        Weather pa4 = new Weather(now.plusDays(3),10, 13,50, 77, 16, Portalegre);
        Weather pa5 = new Weather(now.plusDays(4),11, 15,20, 73, 16, Portalegre);
        this.weatherRepo.save(pa1);
        this.weatherRepo.save(pa2);
        this.weatherRepo.save(pa3);
        this.weatherRepo.save(pa4);
        this.weatherRepo.save(pa5);
        Portalegre.addWeather(pa1);
        Portalegre.addWeather(pa2);
        Portalegre.addWeather(pa3);
        Portalegre.addWeather(pa4);
        Portalegre.addWeather(pa5);
        this.citiesRepo.save(Portalegre);

        City Lisboa = new City("lisboa", "Center");
        Weather ls1 = new Weather(now,8, 14,2, 74, 16, Lisboa);
        Weather ls2 = new Weather(now.plusDays(1),9, 16,10, 75, 13, Lisboa);
        Weather ls3 = new Weather(now.plusDays(2),11, 16,90, 81, 26, Lisboa);
        Weather ls4 = new Weather(now.plusDays(3),12, 15,60, 79, 19, Lisboa);
        Weather ls5 = new Weather(now.plusDays(4),12, 17,40, 78, 24, Lisboa);
        this.weatherRepo.save(ls1);
        this.weatherRepo.save(ls2);
        this.weatherRepo.save(ls3);
        this.weatherRepo.save(ls4);
        this.weatherRepo.save(ls5);
        Lisboa.addWeather(ls1);
        Lisboa.addWeather(ls2);
        Lisboa.addWeather(ls3);
        Lisboa.addWeather(ls4);
        Lisboa.addWeather(ls5);
        this.citiesRepo.save(Lisboa);


        City Evora = new City("evora", "South");
        Weather e1 = new Weather(now,7, 13,2, 84, 18, Evora);
        Weather e2 = new Weather(now.plusDays(1),7, 14,20, 80, 11, Evora);
        Weather e3 = new Weather(now.plusDays(2),9, 16,90, 81, 18, Evora);
        Weather e4 = new Weather(now.plusDays(3),11, 15,50, 80, 18, Evora);
        Weather e5 = new Weather(now.plusDays(4),11, 17,20, 77, 18, Evora);
        this.weatherRepo.save(e1);
        this.weatherRepo.save(e2);
        this.weatherRepo.save(e3);
        this.weatherRepo.save(e4);
        this.weatherRepo.save(e5);
        Evora.addWeather(e1);
        Evora.addWeather(e2);
        Evora.addWeather(e3);
        Evora.addWeather(e4);
        Evora.addWeather(e5);
        this.citiesRepo.save(Evora);

        City Setubal = new City("setubal", "South");
        Weather st1 = new Weather(now,7, 16,2, 76, 21, Setubal);
        Weather st2 = new Weather(now.plusDays(1),8, 16,10, 80, 13, Setubal);
        Weather st3 = new Weather(now.plusDays(2),10, 17,90, 84, 26, Setubal);
        Weather st4 = new Weather(now.plusDays(3),11, 15,60, 82, 19, Setubal);
        Weather st5 = new Weather(now.plusDays(4),12, 17,40, 80, 23, Setubal);
        this.weatherRepo.save(st1);
        this.weatherRepo.save(st2);
        this.weatherRepo.save(st3);
        this.weatherRepo.save(st4);
        this.weatherRepo.save(st5);
        Setubal.addWeather(st1);
        Setubal.addWeather(st2);
        Setubal.addWeather(st3);
        Setubal.addWeather(st4);
        Setubal.addWeather(st5);
        this.citiesRepo.save(Setubal);

        City Beja = new City("beja", "South");
        Weather bj1 = new Weather(now,6, 12,44, 90, 19, Beja);
        Weather bj2 = new Weather(now.plusDays(1),6, 14,10, 79, 11, Beja);
        Weather bj3 = new Weather(now.plusDays(2),8, 16,80, 81, 19, Beja);
        Weather bj4 = new Weather(now.plusDays(3),10, 14,40, 82, 19, Beja);
        Weather bj5 = new Weather(now.plusDays(4),9, 16,40, 81, 23, Beja);
        this.weatherRepo.save(bj1);
        this.weatherRepo.save(bj2);
        this.weatherRepo.save(bj3);
        this.weatherRepo.save(bj4);
        this.weatherRepo.save(bj5);
        Beja.addWeather(bj1);
        Beja.addWeather(bj2);
        Beja.addWeather(bj3);
        Beja.addWeather(bj4);
        Beja.addWeather(bj5);
        this.citiesRepo.save(Beja);

        City Faro = new City("faro", "South");
        Weather f1 = new Weather(now,10, 16,95, 93, 19, Faro);
        Weather f2 = new Weather(now.plusDays(1),10, 17,20, 72, 16, Faro);
        Weather f3 = new Weather(now.plusDays(2),12, 17,90, 79, 21, Faro);
        Weather f4 = new Weather(now.plusDays(3),13, 16,90, 78, 18, Faro);
        Weather f5 = new Weather(now.plusDays(4),14, 18,90, 84, 32, Faro);
        this.weatherRepo.save(f1);
        this.weatherRepo.save(f2);
        this.weatherRepo.save(f3);
        this.weatherRepo.save(f4);
        this.weatherRepo.save(f5);
        Faro.addWeather(f1);
        Faro.addWeather(f2);
        Faro.addWeather(f3);
        Faro.addWeather(f4);
        Faro.addWeather(f5);
        this.citiesRepo.save(Faro);

        City Madeira = new City("madeira", "Isle");
        Weather m1 = new Weather(now,6, 8,85, 73, 13, Madeira);
        Weather m2 = new Weather(now.plusDays(1),8, 12,60, 71, 16, Madeira);
        Weather m3 = new Weather(now.plusDays(2),5, 11,90, 65, 21, Madeira);
        Weather m4 = new Weather(now.plusDays(3),6, 10,90, 70, 18, Madeira);
        Weather m5 = new Weather(now.plusDays(4),7, 10,80, 72, 23, Madeira);
        this.weatherRepo.save(m1);
        this.weatherRepo.save(m2);
        this.weatherRepo.save(m3);
        this.weatherRepo.save(m4);
        this.weatherRepo.save(m5);
        Madeira.addWeather(m1);
        Madeira.addWeather(m2);
        Madeira.addWeather(m3);
        Madeira.addWeather(m4);
        Madeira.addWeather(m5);
        this.citiesRepo.save(Madeira);

        City Acores = new City("açores", "Isle");
        Weather ares1 = new Weather(now,14, 16,1, 64, 13, Acores);
        Weather ares2 = new Weather(now.plusDays(1),12, 18,50, 79, 37, Acores);
        Weather ares3 = new Weather(now.plusDays(2),12, 14,10, 64, 42, Acores);
        Weather ares4 = new Weather(now.plusDays(3),14, 16,50, 76, 511, Acores);
        Weather ares5 = new Weather(now.plusDays(4),14, 17,40, 78, 37, Acores);
        this.weatherRepo.save(ares1);
        this.weatherRepo.save(ares2);
        this.weatherRepo.save(ares3);
        this.weatherRepo.save(ares4);
        this.weatherRepo.save(ares5);
        Acores.addWeather(ares1);
        Acores.addWeather(ares2);
        Acores.addWeather(ares3);
        Acores.addWeather(ares4);
        Acores.addWeather(ares5);
        this.citiesRepo.save(Acores);

    }


}
