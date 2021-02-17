function Form() {
    return (
      <form>
        <div className="mb-3">
          <label htmlFor="address" className="form-label">Address</label>
          <input type="text" name="address" className="form-control" />
        </div>
      </form>
    );
}

export default Form;