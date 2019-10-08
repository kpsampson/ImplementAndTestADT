
///////////////////////////////////////////////////////////////////////////////
//
// Title:           p1 Implement and Test an ADT
//
// Files: 			DS_My.java, DataStructureADTTest.java, TestDS_My.java
// 
// Course:          CS 400, 001, Fall 2019
//
// Author:          Kylie Sampson
// Email:           kpsampson@wisc.edu  
//
///////////////////////////////////////////////////////////////////////////////
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class runs a series of test to test the functionality of different ADTs.
 * 
 * @author Kylie Sampson
 *
 * @param <T>
 */
abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {

	private T dataStructureInstance;

	protected abstract T createInstance();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception { // before each test a new instance is created
		dataStructureInstance = createInstance();
	}

	@AfterEach
	void tearDown() throws Exception {
		dataStructureInstance = null; // after each test the instance is set to null, then reset before the next
	}

	/**
	 * This method checks that a new data structure has a size of zero. It makes a
	 * call to size then checks that it is equal to zero.
	 */
	@Test
	void test00_empty_ds_size() {
		if (dataStructureInstance.size() != 0) // calls the size and compares it to 0
			fail("data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
	}

	/**
	 * This test tests that size is incremented when a pair is added.
	 */
	@Test
	void test01_after_insert_one_size_is_one() {
		dataStructureInstance.insert("first", "one");
		if (dataStructureInstance.size() != 1)
			fail("data structure should have one pair, with size=1, but size=" + dataStructureInstance.size());
	}

	/**
	 * This test checks that when a pair is removed the size is decremented
	 */
	@Test
	void test02_after_insert_one_remove_one_size_is_0() {
		dataStructureInstance.insert("first", "value");
		dataStructureInstance.remove("first");
		if (dataStructureInstance.size() != 0)
			fail("data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
	}

	/**
	 * Duplicate keys are not allowed. This test checks that if a duplicate key is
	 * inserted then a runtime exception is thrown
	 */
	@Test
	void test03_duplicate_exception_is_thrown() {
		try {
			dataStructureInstance.insert("key", "value");
			dataStructureInstance.insert("other", "value");
			dataStructureInstance.insert("key", "value"); // duplicate key value inserted
		} catch (RuntimeException e) {
			return;
		}
		fail("no excception was thrown when duplicate keys were entered");
	}

	/**
	 * Remove() returns true when a value was successfully removed and false
	 * otherwise. If a key that is not in the structure is passed, the method should
	 * return false. This test checks that false is returned.
	 */
	@Test
	void test04_remove_returns_false_when_key_not_present() {
		boolean test04_Returned;
		dataStructureInstance.insert("key", "value");
		test04_Returned = dataStructureInstance.remove("hello");
		if (test04_Returned)
			fail("the remove function did not return false when a key that has not been entered was called");
	}

	/**
	 * The key cannot be null, though the value can. This test checks that null can
	 * be inserted as a value without throwing an exception.
	 */
	@Test
	void test05_insert_null_value_is_accepted() {
		try {
			dataStructureInstance.insert("yay", null);
		} catch (Exception e) {
			fail("null was not accepted as a value and threw an exception");
		}
	}

	/**
	 * Keys cannot be set to null. This test checks that the null value passed in is
	 * handled without throwing an exception.
	 */
	@Test
	void test06_contains_returns_false_if_key_is_null() {
		boolean test06_ReturnsFalse;
		test06_ReturnsFalse = dataStructureInstance.contains(null);
		if (test06_ReturnsFalse)
			fail("when contains is called with a null key, it should return false");
	}

	/**
	 * Contains returns true when the key is in the data structure, and false when
	 * it is not. This tests test a key that is not in the data structure.
	 */
	@Test
	void test07_contains_returns_false_when_key_is_not_present() {
		boolean test07_ReturnsFalse = true;
		dataStructureInstance.insert("key1", "value1");
		dataStructureInstance.insert("key2", "value2");
		test07_ReturnsFalse = dataStructureInstance.contains("hello");
		if (test07_ReturnsFalse)
			fail("contains does not return false when the key is not present");
	}

	/**
	 * This method tests that get() returns an IllegalArguementException when null
	 * is passed in
	 */
	@Test
	void test08_get_throws_exception_when_key_is_null() {
		try {
			dataStructureInstance.get(null);
		} catch (IllegalArgumentException e) {
			return;
		}
		fail("get did not throw an exception when null was passed in as k");
	}

	/**
	 * Contains returns true when the key is present and false otherwise. This
	 * checks that if the key is in the data structure that contains correctly
	 * returns true
	 */
	@Test
	void test09_contains_returns_true_when_key_is_in_data_structure() {
		dataStructureInstance.insert("key", "value");
		dataStructureInstance.insert("key2", "value2");
		dataStructureInstance.insert("key3", "value3");
		boolean test09_Return;
		test09_Return = dataStructureInstance.contains("key");
		if (!test09_Return) {
			fail("contains returned false for a value that was added");
		}
	}

	/**
	 * Test checks that the correct value is returned to the method when there are
	 * several nodes
	 */
	@Test
	void test10_get_returns_correct_value_when_key_called() {
		dataStructureInstance.insert("key", "value");
		dataStructureInstance.insert("key2", "value2");
		dataStructureInstance.insert("key3", "value3");

		Object test10_ReturnedValue = dataStructureInstance.get("key");
		if (!test10_ReturnedValue.equals("value")) {
			fail("value returned from get method did not match expected value for key");
		}
	}

	/**
	 * The key cannot be null. If a pair is created with a null key, an
	 * IllegalArgumentException should be thrown
	 */
	@Test
	void test11_insert_null_key_throws_IllegalArgumentException() {
		try {
			dataStructureInstance.insert(null, "value");
		} catch (IllegalArgumentException e) {
			return;
		}
		fail("null value entered for key, no IllegalArgumentException was thrown");
	}

	/**
	 * Duplicate key values are not allowed. If a duplicate key is inserted a
	 * duplicate key exception is thrown. This test checks that the original value
	 * is saved for the key and that it is not updated
	 */
	@Test
	void test12_duplicate_key_did_not_update_value() {
		dataStructureInstance.insert("key", "first");
		Object test12_get1 = dataStructureInstance.get("key");
		try {
			dataStructureInstance.insert("key", "second");
		} catch (RuntimeException e) {
			if (!dataStructureInstance.get("key").equals(test12_get1)) {
				fail("adding a duplicate key changed the original value");
			}
		}
	}

	/**
	 * This test checks that an IllegalArgumentException is thrown when remove is
	 * called with a null key. Keys cannot be created with a null value.
	 */
	@Test
	void test13_remove_is_called_with_null_key_exception_thrown() {
		try {
			dataStructureInstance.remove(null);
		} catch (IllegalArgumentException e) {
			return;
		}
		fail("no exception was thrown for passing null as a key to remove");
	}

	/**
	 * This test checks that the size does not change when remove is called with a
	 * null key value.
	 */
	@Test
	void test14_check_size_when_null_removed() {
		dataStructureInstance.insert("key", "value");
		dataStructureInstance.insert("key2", "value2");
		dataStructureInstance.insert("key3", "value3");
		int preSize = dataStructureInstance.size(); // size before remove is called
		try {
			dataStructureInstance.remove(null);
		} catch (IllegalArgumentException e) {
			if (dataStructureInstance.size() != preSize) // compares the current size to the size before remove
				fail("removing a null value should not change the size. Expected: " + preSize + " Returned: "
						+ dataStructureInstance.size());
		}
	}

	/**
	 * Pairs can store null for value. This method checks that null can be set as
	 * the value without throwing an exception.
	 */
	@Test
	void test15_pair_stores_null_value() {
		try {
			dataStructureInstance.insert("key", null);
		} catch (Exception e) {
			fail("null was not returned properly with get when stored as a value");
		}
	}

	/**
	 * get() should return the value associated with the key passed in, without
	 * affecting the size. This method checks the size prior to calling get(), then
	 * again after, to ensure no change.
	 */
	@Test
	void test16_get_does_not_change_size() {
		dataStructureInstance.insert("key", "value");
		dataStructureInstance.insert("key2", "value2");
		dataStructureInstance.insert("key3", "value3");
		int preSize = dataStructureInstance.size();
		dataStructureInstance.get("key");

		if (dataStructureInstance.size() != preSize)
			fail("calling get should not change the size. Expected: " + preSize + " Returned: "
					+ dataStructureInstance.size());
	}

	@Test
	// test17_after_key_removed_cannot_be_called_again
	void test17_after_key_removed_cannot_be_called_again() {
		dataStructureInstance.insert("key", "value");
		dataStructureInstance.insert("key2", "value2");
		dataStructureInstance.insert("key3", "value3");
		dataStructureInstance.remove("key");
		if (dataStructureInstance.get("key") != null) {
			fail("key was removed, should not return a value");
		}
	}

	/**
	 * When the remove method is passed a null key it should throw an
	 * IllegalArgumentException. This test checks that the proper exception is being
	 * thrown.
	 */
	@Test
	void test18_removing_null_pair_throws_correct_exception() {
		try {
			dataStructureInstance.remove(null);
		} catch (IllegalArgumentException e) {
			return;
		} catch (Exception e) {
			fail("IllegalArgumentException should be thrown");
		}
	}

	/**
	 * Pairs can retrieve null value. This method checks that get() can return a
	 * null value without throwing an exception.
	 */
	@Test
	void test19_get_returns_null_values() {
		try {
			dataStructureInstance.insert("key", null);
			dataStructureInstance.get("key");
		} catch (Exception e) {
			fail("null was not returned properly with get when stored as a value");
		}
	}

	/**
	 * Remove returns a boolean. This test checks that when a value is properly
	 * removed that true is returned.
	 */
	@Test
	void test20_remove_returns_true_when_key_removed() {
		boolean test04_Returned;
		dataStructureInstance.insert("key", "value");
		test04_Returned = dataStructureInstance.remove("key");
		if (!test04_Returned)
			fail("the remove function returned false when a key was removed, true expected");
	}

	/**
	 * When the get method is called with a value not in the data structure it
	 * should be handled without throwing an exception.
	 */
	@Test
	void test21_get_called_with_value_not_in_list() {
		try {
			dataStructureInstance.get("hello");
		} catch (Exception e) {
			fail("a non present value caused an exception, this should be handled in code");
		}

	}
}
