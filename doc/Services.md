### VehicleService

VehicleService provides get/add/edit/delete functions of Vehicles for both Dealer and Customer

```
Inventory getVehiclesByFilter(String dealerID, Filter filter, Sorting sorting, Paging paging);
// Return the Inventory from one dealer after filter, sorting and paging
```

```
String addVehicle(String dealerID, Vehicle v);
// Return the ID of the new vehicle
// Notice that here the input vehicle could not contain id attribute
```

```
boolean editVehicle(String dealerID, Vehicle v);
// Return true if edit successfully.
```

```
boolean removeVehicle(String dealerID, String vehicleID);
```

### DealerService

DealerService provides get function of Dealer for customer

```
Collection<Dealer> getAllDealers();
```