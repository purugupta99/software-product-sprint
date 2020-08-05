// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.util.Collection;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;

import com.google.sps.TimeRange;

public final class FindMeetingQuery {
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    // throw new UnsupportedOperationException("TODO: Implement this method.");
    
    Collection<TimeRange> result = new ArrayList<TimeRange>();
    
    // Get all attendees and meeting duration
    Collection<String> attendees = request.getAttendees();
    long meetingDuration = request.getDuration();
    int size = 24 * 60;
    
    // Array of output timestamps
    List<Integer> timestamps = new ArrayList<Integer>();
    
    // Collection of schedule of all attendees
    HashMap<String, boolean[]> schedules = new HashMap<String, boolean[]>();
    
    //Common Timeline
    boolean[] commonTimeline = new boolean[size];
    boolean[] unionOfSchedules = new boolean[size];
    
    // Initialize such that all time is currently available for meeting
    Arrays.fill(commonTimeline, true);
    
    // If no attendees, all time can be alloted
    if (attendees.isEmpty()){
      result.add(TimeRange.WHOLE_DAY);
      return result;
    }
    
    // If meeting duration is more than complete day, no time cna be alloted
    if (meetingDuration > TimeRange.WHOLE_DAY.duration()){
      return result;
    }
    
    // Initialize schedules for all attendees
    for(String s: attendees){
      schedules.put(s, new boolean[size]);
    }
    
    // Blocking schedules of attendees based on their involvement in all events
    for (Event e: events) {
      Set<String> event_attendees = e.getAttendees();
      int start = e.getWhen().start();
      int end = e.getWhen().end();
      
      for(String s: event_attendees){
        
        if(schedules.get(s) == null){
          continue;
        }

        boolean[] currSchedule = schedules.get(s);
        
        for(int i=start; i<end; i++){
          currSchedule[i] = true;
        }
        
        schedules.put(s, currSchedule);
      }
    }
    
    
    // Blocking time from common timeline based on the availability of the attendees
    for(String s: attendees){
      
      boolean[] tempSchedule = schedules.get(s);
      for(int i=0; i<size; i++){
        unionOfSchedules[i] |= tempSchedule[i];
      }
    }
    
    // Marking the common time available for all attendees
    for(int i=0; i<size; i++){
      commonTimeline[i] ^= unionOfSchedules[i];
    }
    
    // Converting common timeline to time ranges
    for(int i=0; i<size; i++){
      if(i == 0 && commonTimeline[i]){
        // System.out.print(i + " ");
        timestamps.add(i);
        
      }
      
      else if(i == size-1 && commonTimeline[i]){
        // System.out.print(i + " ");
        timestamps.add(i+1);
      }
      
      else if(commonTimeline[i] && !commonTimeline[i-1]){
        // System.out.print(i + " ");
        timestamps.add(i);
      }
      
      else if(commonTimeline[i] && !commonTimeline[i+1]){
        // System.out.print(i);
        timestamps.add(i+1);
      }
    }
    
    for(int i=0; i<timestamps.size(); i+=2){
      // System.out.println(timestamps.get(i) + " " + timestamps.get(i+1));
      
      if (meetingDuration <= timestamps.get(i+1) - timestamps.get(i)){
        result.add(TimeRange.fromStartEnd(timestamps.get(i), timestamps.get(i+1), false));
      }
    }
    
    // System.out.println("");
    
    return result;
  }
}
