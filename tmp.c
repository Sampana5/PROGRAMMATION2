    enum {
      SMALL_WINDOW_SIZE = 1500,
      MEDIUM_WINDOW_SIZE = 5000,
      LARGE_WINDOW_SIZE = 10000
    };
     
    #define DATASET_PATH_TESTDBGEN2 "../testdb_gen2.txt"
    #define DATASET_NUMBER_ITEMS 1001
     
    #define EXPECTED_RESULT_TDBGEN2_SMALL_WINDOW 78765
    
    
    
   void test_first() {
  const char* source = DATASET_PATH_TESTDBGEN2;
  char* const sourcePtr = _strdup(source);
  const uint32_t result = start_ciclad(sourcePtr, SMALL_WINDOW_SIZE, DATASET_NUMBER_ITEMS, 0);
  CU_ASSERT_EQUAL(result, EXPECTED_RESULT_TDBGEN2_SMALL_WINDOW);
}
 
void test_second() {
  const char* source = DATASET_PATH_TESTDBGEN2;
  char* const sourcePtr = _strdup(source);
  const uint32_t result = start_ciclad(sourcePtr, MEDIUM_WINDOW_SIZE, DATASET_NUMBER_ITEMS, 0);
  CU_ASSERT_EQUAL(result, EXPECTED_RESULT_TDBGEN2_SMALL_WINDOW);
}
