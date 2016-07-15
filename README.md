# Event Organizer

#####- 1. Description
#####- 2. Roadmap and Current State
#####- 3. Use
#####- 4. Future Goals
#####  - 4.1 Improvements to Current Interface
#####  - 4.2 Additional Planned Features


## 1. Description

Event Organizer is an Android application designed to simplify the coordination of informal events.  As important information is invariably buried in group texts, EO is an attempt to store and sync these details between all involved parties.  The application is currently only partially functional.

## 2. Roadmap and Current State

* Create, destroy, and modify an unlimited number of events  -- complete
* Create, destroy, and modify an unlimited number of details for each event -- complete
* Persist data between user sessions  -- complete
* Share events with other users  -- incomplete
* Syncronize data between users  -- incomplete
* Integrate with Google Calendars
  * Log availabilities of various users
  * Display periods of greatest shared availability

## 3. Use

* Events Activity
  * Select any card marked "New Event" to create another card
  * For any created card, location and time information will be displayed here
  * Select a card to view its details
* Details Activity
  * Enter text in a field near the top, click save to name the current event
  * Select "Click to add detail" to create a new card
  * Select a current detail to add information to it
  * Select "DELETE EVENT" in order to destroy the currently selected event
* Add to Details Activity
  * Select detail type from spinner at the top
  * Add in relevent text or time information
  * Save, cancel, and delete buttons are found at the top

## 4. Future Goals

4.1 Improvements to Current Interface

* Add to Details Activity color scheme needs to be further cleaned
* Add to Details Activity spinner blends in with the background
* Add to Details Activity will eventually be replaced with another ListView
  * As part of this redesign, there will only be one detail card of each type.  These will not show up until populated, and expand as needed


4.2 Additional Planned Features

* Users will be able to share a created event with friends
* Changes to this event made by any user will be propagated to all
* General availability times will be inferred from Google Calendar, and suggested meeting times displayed overlaying a chart


