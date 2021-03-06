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

function addRandomGreeting() {
  const greetings =
      ['I am 19 years old.', 'I like to hang out with my friends.', 'I have been coding since I was about 14 years old.', 'I am Hispanic!', 'I have been in 3 of 4 American time zones!'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

function addMyComments() {
  fetch('/data').then(response => response.json()).then((myComment) => {

    const commentsListElement = document.getElementById('comments-container');
    commentsListElement.innerHTML = '';

    for( let element in myComment){
        var node = createListElement(myComment[element].comment);
        commentsListElement.appendChild(node);

    }
  });
}

function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}