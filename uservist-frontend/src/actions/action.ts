// Action method types
import {AxiosError, AxiosResponse} from "axios";
import ReducerMessage from "../models/reducerMessage";

export const GET = 'GET';
export const POST = 'POST';
export const PUT = 'PUT';
export const DELETE = 'DELETE';

/**
 * A method type for axios http request to the API.
 * Requests available are: GET, POST, PUT, DELETE.
 */
export type ReducerActionMethod = typeof GET | typeof POST | typeof PUT | typeof DELETE;

/**
 * Reducer payload request interface.
 * When user makes a request this object must be present with fields set.
 */
export interface ReducerActionPayloadRequest {
  /**
   * URL to the request endpoint is required.
   */
  url: string;

  /**
   * Method type is also required.
   */
  method: string;

  /**
   * Data is optional and not required for GET requests.
   */
  data?: Object;

  /**
   * Define whether request made to backend requires a token to be passed by.
   */
  useAuth?: boolean;
}

/**
 * Generic payload received and passed from and to a reducer action.
 */
export interface ReducerActionPayload<A> {
  /**
   * In making a request, client defines the name of the client. (not required for 1 client)
   */
  client?: string;

  /**
   * A payload request, used to define properties for user request
   * to be made to the API endpoint.
   */
  request: ReducerActionPayloadRequest & Object;
}

/**
 * A generic axios object that handles data sent via requests and received by reducers.
 * This object must be returned from each action called for reducer.
 * This interface can be extended. Some of the values present here are not available in reducer
 * because they are depending on the result of the request:
 * 1. On SUCCESS:
 *    {
 *      type: 'AWESOME',
 *      payload: { ... },
 *      meta: {
 *        previousAction: { ... }
 *      }
 *    }
 * 2. On ERROR:
 *    {
 *      type:'OH_NO',
 *      error: { ... },
 *      meta: {
 *        previousAction: { ... }
 *      }
 *    }
 * 3. On EXCEPTION:
 *    {
 *      type:'OH_NO',
 *      error: {
 *        status: 0,
 *        ...,
 *      },
 *      meta: {
 *        previousAction: { ... }
 *      }
 *    }
 */
export interface ReducerAction<A extends AxiosResponse | ReducerActionPayload<any>> {
  type?: string;
  types?: string[];
  payload: A;
  error?: AxiosError<ReducerMessage>;
  meta?: { previousAction: Object };
}

/**
 * This class represents a type which is passed to reducer action.
 * The action function receives this action and it is later used in reducer to determine the type
 * of request received in axios.
 */
export class ReducerActionTypes {
  public REQUEST: string;
  public SUCCESS: string;
  public FAILURE: string;

  constructor(type: string) {
    this.REQUEST = type + '_REQUEST';
    this.SUCCESS = type + '_SUCCESS';
    this.FAILURE = type + '_FAIL';
  }

  typesArray() {
    return [this.REQUEST, this.SUCCESS, this.FAILURE];
  }
}