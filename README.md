** WORK IN PROGRESS **

# Musicode
Converts text into a series of musical notes and plays them

Disclaimer: This is steganography, not encryption. It's not a secure way to handle sensitive data, and was just built for fun. The "decode" function is just the "encode" function reverse-engineered, which is not hard to do for anyone who knows how it works.

Note: I didn't put in all the incorrect input handling yet so can you just not input wrong pls and thank you have a nice day

## How To Use It

### Using the MusicodeUI App

1. **Compile the code:**
   Make sure all Java files in the `mainpackage` directory are compiled. For example:
   ```sh
   javac mainpackage/*.java
   ```

2. **Run the UI:**
   ```sh
   java mainpackage.MusicodeUI
   ```

3. **Instructions:**
   - Select a musical key from the dropdown at the top.
   - Enter plaintext (to encode) or a sequence of notes (to decode) in the input area.
   - Click **Encode** to convert plaintext to musical notes. The result will appear in the output area.
   - Click **Decode** to convert a sequence of notes back to plaintext. The result will appear in the output area.
   - Click **Play** to play the notes currently shown in the output area.

**Note:** Only use valid key names from the dropdown. The app does not handle all possible invalid inputs yet.
