# MYT
MyYandexTrains

In russian
Эта программа windows-based программа позволяет задать требуемый маршрут и получать оповещения в трее о поездах по этому маршруту.
Расписание берется из Яндекс АПИ.

В конфиге задается:
<!--in minutes, time to get nearest departure station or railway--> - время, сколько надо, чтобы дойти до ближайшей станции;
<!--in minutes, period before depare and timeToGetDepStation--> - сколько времени до момента выхода надо оповещать;
<!--in minutes, create notification every ... --> - с какой частотой оповещать

Живет в трее. 
Пока там не выбран не один поезд - отображает просто список ближайших.
Если выбран - напоминает, когда выходить.

Так как АПИ Яндекс Расписаний относительно стабильно - с конца 16 года работает.
Если надо что поменять - готов чтонить дописать.
Писал для себя, чтобы опробовать java и поработать с сервисами.
