# MegaBud
Mega budget app interview test.

This is all I managed to complete and it took me well over 4 hours:
- Basic tabbed navigation.
- Placeholder screen for charts.
- Unifinished transaction screens: list and create with loading of exchange rate from provided API.
- Unfinished category screens with name, colour, budget and currency.
- Database implemented using Room.
- Access to API endpoints implemented using Retrofit.

What is missing:
- Validation: the forms should have checks for each of the inputs and should assing and error to their respective views when input is not valid.
- Constraints: I did not get to enforce the relation between transactions and categories. Transactions should not be created without existing categories. Removing categories would leave orphan transactions and needs to be dealt with.
- No charts: I simply didn't get to do this.
- State management: I've only managed to implement a rudimentary state representation for the Transactions screen using an enum and LiveData. In a production app I would have used a sealed class to represent different events and a data class to represent an observable stream of immutable states (like in Redux). 
- Error handling: I have not managed to handle errors. I would have displayed and error message if the network or database transactions failed.
- Displaying labels in dark or light colours depending on the category background: I simply didn't get to do this, I would hope I could use an existing utility class or library exists that can suggest the right text colour for a given background colour.
