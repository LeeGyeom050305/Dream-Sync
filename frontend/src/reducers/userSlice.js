import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  isAuthenticated: false,
  homeUri: '',
};

const userSlice = createSlice({
  name: 'user',
  initialState,
  reducers: {
    setAuthenticated: (state, action) => {
      state.isAuthenticated = action.payload;
    },
    setHomeUri: (state, action) => {
      state.homeUri = action.payload;
    },
  },
});

export const { setAuthenticated, setHomeUri } = userSlice.actions;

export default userSlice.reducer;
