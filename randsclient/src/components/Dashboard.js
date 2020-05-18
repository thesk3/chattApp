import React, { Component } from "react";
import { connect } from "react-redux";
import logo from '../image/wallpaper.jpg';
import { UserList,UserMessages,addToUserList } from "../actions/securityActions";
import axios from "axios";
import ChatWindow from "./ChatWindow"
import {OPEN_CHAT_USER } from "../actions/types";
import store from "../store";
//import MediaQuery from 'react-responsive';

import "./MessageInput.css";

//import PropTypes from "prop-types";

class Dashboard extends Component {

  constructor(props) {
    super(props);
   
    this.state = {
      userData: {},
      showData:false,
      addUserUI:false,
      name:"name",
      id:0,
      showSide:true,
      showMain:true
      
    };
    this.onAction = this.onAction.bind(this);
    // this.onSubmit = this.onSubmit.bind(this);
    this.handleChangeId = this.handleChangeId.bind(this);
    this.handleChangeName = this.handleChangeName.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.showSideBar= this.showSideBar.bind(this);
    this.showMainBar= this.showMainBar.bind(this);
    
  }
  
  async  componentDidMount() {
    const state = store.getState();
    const { security } = state;
    var  toID = parseInt(security.user.id, 10);

    var returndata=this.props.UserList(toID);
    const res = await axios.get("http://localhost:8080/users/getUserList", {
      params: {
        id :toID
            }});
console.log("state--->",state);
    this.setState({userData:res.data.data });
     
  
  //  this.props.userData=res.data.data;
//    console.log("data--->", this.state.userData.length);

  }
  onAction= (data) => {
    console.log("on action--->", data);
    const state = store.getState();
    const { security } = state;
    var  toID = parseInt(security.user.id, 10);
 
      var senddata={
        from:toID,
        to:data.userID
      }
      console.log("store-->",senddata);

    this.setState({showData:true });
    this.setState({showSide: this.state.showSide?false:true});
  
    this.props.UserMessages(senddata);

      const interval = setInterval(() => {
        if (true) {
        //  console.log("in interva")
        window.scrollTo(0, 0);

        // uncemnet below line to make contoinus live msg
        // this.props.UserMessages(senddata);
          //clearInterval(interval);
        };
      }, 3000);


    store.dispatch({
       type: OPEN_CHAT_USER,
       payload: data
     })

  } 
   addToUSerList= () => {
     console.log("add to user--->");
     
    this.setState({addUserUI: this.state.addUserUI?false:true});
     
  }

  handleChangeName(event) {
    console.log("in name",event.target);

    this.setState({name: event.target.value});
   }
   handleChangeId(event) {
    console.log("in id",event.target.id);

    this.setState({id: event.target.value});
   }
   
  handleSubmit(event) {
    event.preventDefault();
    console.log('A name was submitted: ' , this.state);

    const state = store.getState();
    const { security } = state;
    var  toID = parseInt(security.user.id, 10);
    var  listID = parseInt(this.state.id, 10);
 
      var senddata={
        userID:toID,
        listID,
        name:this.state.name,

        
      }
      console.log("store-->",senddata);

     this.props.addToUserList(senddata);
   



  }
  componentWillReceiveProps() {
    //console.log("compone will recive-->",this.props.userData);

  }

  showSideBar(){
    this.setState({showSide: this.state.showSide?false:true});
    
  }
  showMainBar(){
    console.log("ist main--> ",this.state.showMain);
    this.setState({showMain: this.state.showMain?false:true});
    
  }

  render() {

    if (this.state.userData.length > 0) {
      var list = <ul className="userList">
        {this.state.userData.map((post, i) => (
          
          <li key={post.userID} >
            <img src={logo} className="imgCir" alt="Canvas Logo" ></img>
            <label className="use-name" onClick={() =>this.onAction(post)}>{post.userName}</label></li>

        ))}</ul>;
    } else {
      list = <ul className="userList">

        <li>
          <img src={logo} className="imgCir" alt="Canvas Logo" ></img>
          <label className="use-name">static</label></li>

      </ul>;
     
    }
    if( this.state.addUserUI  ){
      var addUser =<div>

<form className="Message pad-9" onSubmit={this.handleSubmit}>

<div className="form-group">

<input
      className="form-control input-sm"
        onChange={this.handleChangeId}
        value={this.state.id}  
        placeholder="Id"
      />
  
</div>

      	<div className="form-group">
        <input
      className="form-control input-sm"
      onChange={this.handleChangeName}
      value={this.state.name}  
      placeholder="Name"
    />

					</div>
    <button type="submit" className="btn btn-primary c-btn">Add User</button>
    </form>

      </div>
    }else{
      addUser=<div></div>
    }

    if(this.state.showSide){
      var side=
      <div className="sideBar">
          
          <div>
    <div style={{textAlign:"center",padding:"9px"}}><label onClick={() =>this.addToUSerList()}>
    {this.state.addUserUI?"Cancel ":"Add User"}</label></div>
    
    <label onClick={() =>this.showMainBar()}>Show main bar</label>
    <div className="on-mobile"><label onClick={() =>this.addToUSerList()}>On mobile</label></div>
        
                  {addUser}
            {list}
    
          </div>
    
        </div>
    }else{
       side=<div>hide side bar </div>

    }
    if(this.state.showMain){
      var main=     <ChatWindow data={this.state.showData}/>
    }else{
       main=<div>hide  minabar</div>

    }


    return (
      <div className="projects">
        <div className="container-fluid">
          <div className="row ">
            <div className="col-md-3 nopadding asd ">
            {side}
       

            </div>

            <div className="col-md-9 cus-sc side-chat">
            <label onClick={() =>this.showSideBar()}>Show side bar</label><br></br>

              <h1 className="display-4 text-center"></h1>
              <br />

              {main}
              <br />
              <hr />

            </div>
          </div>
        </div>
      </div>
    );
  }
}


const mapStateToProps = (state, ownProps) => ({
  //  console.log(state);
  userData: state.user,

});
export default connect(
  mapStateToProps,
  { UserList,UserMessages,addToUserList }
)(Dashboard);
