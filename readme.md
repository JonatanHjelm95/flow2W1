Tirsdag:
Har ikke vedlagt detaljerede besvarelser, men har stadig løst de forskellige opgaver.
 
OneToOne -unidirectional: Der laves en foreignKey i Customer-tabellen til Adresse-tabellen.
OneToMany -unidirecitonal: Der genereres en ny tabel med Customer_ID og address_ID. Desuden bliver der 
tilføjet en Customer i Address-enititeten. 
ManyToMany -bidirectional: Der laves en foreignKey -> Customer_ID i address-tabellen. Desuden bliver der 
også genereret en liste af Customers i address-entiteten.

Onsdag/Torsdag: Fik løst de fleste opgaver. 
Har benyttet PUT til edit/delete endpoints. Delete virker fint, men edit giver en nullpointer og jeg 
orker ikke at kigge mere på den. Endpoints er testet via POSTMAN.
Har ikke lavet nogle tests, udover nogle småting i facadeTest.

Har tilføjet en ny branch (Part2) til sidste del af opgaven.

Fredag:
Har løst alle de grønne opgaver(so far).
Metoder ligger i 2 forskellige facader. Opgaven er lavet i en web application da jeg havde lyst til at 
teste nogle rest-endpoints.
